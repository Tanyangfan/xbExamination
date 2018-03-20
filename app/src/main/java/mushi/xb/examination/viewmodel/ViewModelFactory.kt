package mushi.xb.examination.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import mushi.taibai.util.AppExecutors
import mushi.xb.examination.data.source.local.QuestionsDatabase
import mushi.xb.examination.data.source.local.QuestionsLocalDataSource
import mushi.xb.examination.data.source.local.QuestionsRepository

/**
 * A creator is used to inject the product ID into the ViewModel
 * Created by Tan.Yangfan on 2018/3/19.
 */
class ViewModelFactory(private val mApplication: Application,
                       private val mRepository: QuestionsRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            INSTANCE ?: let {
                synchronized(ViewModelFactory::class) {
                    val database = QuestionsDatabase.getInstance(application.applicationContext)
                    INSTANCE = ViewModelFactory(
                            application, QuestionsRepository.getInstance(
                            QuestionsLocalDataSource.getInstance(AppExecutors(), database.questionDao())))
                }
            }

            return INSTANCE!!
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
            return QuestionViewModel(mApplication, mRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
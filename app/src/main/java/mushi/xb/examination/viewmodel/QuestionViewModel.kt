package mushi.xb.examination.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import mushi.xb.examination.data.Question
import mushi.xb.examination.data.source.QuestionsDataSource
import mushi.xb.examination.data.source.local.QuestionsRepository


/**
 * Start page
 * Created by Tan.Yangfan on 2018/3/19.
 */
class QuestionViewModel(context: Application,
                        private val mQuestionsRepository: QuestionsRepository) : AndroidViewModel(context) {

    @SuppressLint("StaticFieldLeak")
    private val mContext: Context = context.applicationContext

    private val questions: ArrayList<Question> = ArrayList()

    val mIsDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val mIsDataLoadError: MutableLiveData<Boolean> = MutableLiveData()

    fun loadQuestion() {

        mIsDataLoading.value = true
        mIsDataLoadError.value = false

        questions.clear()
        loadSingleOptions()
    }

    private fun loadSingleOptions() {
        mQuestionsRepository.getSingleOptionQuestions(1, object : QuestionsDataSource.LoadQuestionsCallback {
            override fun onLoadSuccess(questions: List<Question>) {
                this@QuestionViewModel.questions.addAll(questions)
                loadMultiOptions()
            }

            override fun onDataNotAvailable() {
                setIsDataLoadError(true)
            }
        })
    }

    private fun loadMultiOptions() {
        mQuestionsRepository.getMultiOptionQuestions(1, object : QuestionsDataSource.LoadQuestionsCallback {
            override fun onLoadSuccess(questions: List<Question>) {
                this@QuestionViewModel.questions.addAll(questions)
                loadJudgments()
            }

            override fun onDataNotAvailable() {
                setIsDataLoadError(true)
            }
        })
    }

    private fun loadJudgments() {
        mQuestionsRepository.getJudgmentQuestions(1, object : QuestionsDataSource.LoadQuestionsCallback {
            override fun onLoadSuccess(questions: List<Question>) {
                this@QuestionViewModel.questions.addAll(questions)
                loadFillBlanks()
            }

            override fun onDataNotAvailable() {
                setIsDataLoadError(true)
            }
        })
    }

    private fun loadFillBlanks() {
        mQuestionsRepository.getFillBlanksQuestions(1, object : QuestionsDataSource.LoadQuestionsCallback {
            override fun onLoadSuccess(questions: List<Question>) {
                this@QuestionViewModel.questions.addAll(questions)
                loadSuccess()
            }

            override fun onDataNotAvailable() {
                setIsDataLoadError(true)
            }
        })
    }

    private fun loadSuccess() {
        mIsDataLoading.value = false
    }

    private fun setIsDataLoadError(isError: Boolean) {
        mIsDataLoading.value = false
        mIsDataLoadError.value = isError
    }

}
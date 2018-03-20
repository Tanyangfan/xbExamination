package mushi.xb.examination.data.source.local

import mushi.taibai.util.AppExecutors
import mushi.xb.examination.data.source.QuestionsDataSource

/**
 * Concrete implementation of a data source as a db.
 * Created by Tan.Yangfan on 2018/3/14.
 */
class QuestionsLocalDataSource(private val mAppExecutors: AppExecutors,
                               private val mQuestionsDao: QuestionsDao) : QuestionsDataSource {

    companion object {
        @Volatile
        private var INSTANCE: QuestionsLocalDataSource? = null

        fun getInstance(appExecutors: AppExecutors, questionsDao: QuestionsDao): QuestionsLocalDataSource {
            INSTANCE ?: let {
                synchronized(QuestionsLocalDataSource::class) {
                    INSTANCE = QuestionsLocalDataSource(appExecutors, questionsDao)
                }
            }

            return INSTANCE!!
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }

    override fun getSingleOptionQuestions(count: Int, callback: QuestionsDataSource.LoadQuestionsCallback) {
        val runnable = Runnable {
            val questions = mQuestionsDao.getSingleOptionQuestions()

            mAppExecutors.mainThread().execute({
                if (questions.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onLoadSuccess(questions)
            })
        }

        mAppExecutors.diskIO().execute(runnable)
    }

    override fun getMultiOptionQuestions(count: Int, callback: QuestionsDataSource.LoadQuestionsCallback) {
        val runnable = Runnable {
            val questions = mQuestionsDao.getMultiOptionQuestions()

            mAppExecutors.mainThread().execute({
                if (questions.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onLoadSuccess(questions)
            })
        }

        mAppExecutors.diskIO().execute(runnable)
    }

    override fun getJudgmentQuestions(count: Int, callback: QuestionsDataSource.LoadQuestionsCallback) {
        val runnable = Runnable {
            val questions = mQuestionsDao.getJudgmentQuestions()

            mAppExecutors.mainThread().execute({
                if (questions.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onLoadSuccess(questions)
            })
        }

        mAppExecutors.diskIO().execute(runnable)
    }

    override fun getFillBlanksQuestions(count: Int, callback: QuestionsDataSource.LoadQuestionsCallback) {
        val runnable = Runnable {
            val questions = mQuestionsDao.getFillBlankQuestions()

            mAppExecutors.mainThread().execute({
                if (questions.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onLoadSuccess(questions)
            })
        }

        mAppExecutors.diskIO().execute(runnable)
    }

}

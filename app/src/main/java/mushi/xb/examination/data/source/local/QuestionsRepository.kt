package mushi.xb.examination.data.source.local

import mushi.xb.examination.data.Question
import mushi.xb.examination.data.source.QuestionsDataSource

/**
 * Concrete implementation to load questions from the data sources into a cache.
 * Created by Tan.Yangfan on 2018/3/19.
 */
class QuestionsRepository(private val mQuestionsLocalDataSource: QuestionsDataSource) : QuestionsDataSource {

    companion object {
        @Volatile
        private var INSTANCE: QuestionsRepository? = null

        fun getInstance(questionsLocalDataSource: QuestionsDataSource): QuestionsRepository {
            INSTANCE?.let {
                synchronized(QuestionsRepository::class) {
                    INSTANCE = QuestionsRepository(questionsLocalDataSource)
                }
            }

            return INSTANCE!!
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }

    override fun getSingleOptionQuestions(count: Int, callback: QuestionsDataSource.LoadQuestionsCallback) {
        mQuestionsLocalDataSource.getSingleOptionQuestions(count, object : QuestionsDataSource.LoadQuestionsCallback {
            override fun onLoadSuccess(questions: List<Question>) {
                callback.onLoadSuccess(questions)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun getMultiOptionQuestions(count: Int, callback: QuestionsDataSource.LoadQuestionsCallback) {
        mQuestionsLocalDataSource.getMultiOptionQuestions(count, object : QuestionsDataSource.LoadQuestionsCallback {
            override fun onLoadSuccess(questions: List<Question>) {
                callback.onLoadSuccess(questions)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun getJudgmentQuestions(count: Int, callback: QuestionsDataSource.LoadQuestionsCallback) {
        mQuestionsLocalDataSource.getJudgmentQuestions(count, object : QuestionsDataSource.LoadQuestionsCallback {
            override fun onLoadSuccess(questions: List<Question>) {
                callback.onLoadSuccess(questions)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun getFillBlanksQuestions(count: Int, callback: QuestionsDataSource.LoadQuestionsCallback) {
        mQuestionsLocalDataSource.getFillBlanksQuestions(count, object : QuestionsDataSource.LoadQuestionsCallback {
            override fun onLoadSuccess(questions: List<Question>) {
                callback.onLoadSuccess(questions)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

}
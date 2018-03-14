package mushi.xb.examination.data.source

import mushi.xb.examination.data.Question

/**
 * Main entry point for accessing question data
 * Created by Tan.Yangfan on 2018/3/14.
 */
interface QuestionsDataSource {

    interface LoadQuestionsCallback {

        fun onLoadSuccess(questions: List<Question>)

        fun onDataNotAvailable()
    }

    /**
     * Get single option
     * @param count 数目
     * @param callback 回调
     */
    fun getSingleOptionQuestions(count: Int, callback: LoadQuestionsCallback)

    /**
     * Get multi option
     * @param count 数目
     * @param callback 回调
     */
    fun getMultiOptionQuestions(count: Int, callback: LoadQuestionsCallback)

    /**
     * Get judgment
     * @param count 数目
     * @param callback 回调
     */
    fun getJudgmentQuestions(count: Int, callback: LoadQuestionsCallback)

    /**
     * Get fill blank
     * @param count 数目
     * @param callback 回调
     */
    fun getFillBlanksQuestions(count: Int, callback: LoadQuestionsCallback)
}

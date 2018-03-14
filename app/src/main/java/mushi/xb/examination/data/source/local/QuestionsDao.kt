package mushi.xb.examination.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import mushi.xb.examination.data.Question

/**
 * Data Access Object for the question table.
 * Created by Tan.Yangfan on 2018/3/13.
 */
@Dao
interface QuestionsDao {

    @Query("SELECT * FROM questions WHERE type = '单选'")
    fun getSingleOptionQuestions(): List<Question>

    @Query("SELECT * FROM questions WHERE type = '多选'")
    fun getMultiOptionQuestions(): List<Question>

    @Query("SELECT * FROM questions WHERE type = '判断'")
    fun getJudgmentQuestions(): List<Question>

    @Query("SELECT * FROM questions WHERE type = '填空'")
    fun getFillBlankQuestions(): List<Question>
}
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

    @Query("SELECT")
    fun getQuestions(): List<Question>
}
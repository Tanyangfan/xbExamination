package mushi.xb.examination.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import mushi.xb.examination.data.Question

/**
 * The Room Database that contains question
 * Created by Tan.Yangfan on 2018/3/13.
 */
@Database(entities = [Question::class], version = 1)
abstract class QuestionsDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: QuestionsDatabase? = null

        fun getInstance(context: Context): QuestionsDatabase {
            INSTANCE ?: let {
                synchronized(QuestionsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            QuestionsDatabase::class.java, "questions.db")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

    /**
     * question操作dao
     */
    abstract fun questionDao(): QuestionsDao

}
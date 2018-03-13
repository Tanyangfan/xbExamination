package mushi.xb.examination.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * 问题表
 * id自增
 * Created by Tan.Yangfan on 2018/3/13.
 */
@Entity(tableName = "questions")
data class Question(
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "type")
        val type: String,
        @ColumnInfo(name = "content")
        val content: String,
        @ColumnInfo(name = "options")
        val options: String,
        @ColumnInfo(name = "answer")
        val answer: String
)
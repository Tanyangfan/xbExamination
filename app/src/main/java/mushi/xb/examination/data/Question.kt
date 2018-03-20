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
data class Question(@ColumnInfo(name = "id")
                    @PrimaryKey(autoGenerate = true)
                    var id: Int = 0) {

    constructor() : this(0)

    @ColumnInfo(name = "type")
    var type: String = ""
    @ColumnInfo(name = "content")
    var content: String = ""
    @ColumnInfo(name = "options")
    var options: String = ""
    @ColumnInfo(name = "answer")
    var answer: String = ""
}
package com.piano.keyboard.synthesia.learnpiano.play.music.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recordEntity")
class NTDOtherRecord(
    @PrimaryKey(
        autoGenerate = true
    )
    val id: Int?,
    @ColumnInfo(name = "file_name")
    var fileName: String,
    @ColumnInfo(name = "file_path")
    var filePath: String,
    @ColumnInfo(name = "duration_time")
    var durationTime: Long,
    @ColumnInfo(name = "created_at")
    var createdAt: Long,
):Serializable
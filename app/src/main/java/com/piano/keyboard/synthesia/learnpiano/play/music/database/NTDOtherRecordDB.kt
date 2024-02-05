package com.piano.keyboard.synthesia.learnpiano.play.music.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NTDOtherRecord::class], version = 1, exportSchema = false)
abstract class NTDOtherRecordDB : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: NTDOtherRecordDB? = null
        private const val DATABASE_NAME = "record.db"

        fun getInstance(context: Context): NTDOtherRecordDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): NTDOtherRecordDB {
            return Room.databaseBuilder(
                context.applicationContext,
                NTDOtherRecordDB::class.java, DATABASE_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

    }

    abstract fun recordDAO(): NTDOtherRecordDAO
}
package com.ntd.appid1.appid2.appid3.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NTDOtherRecordDAO {
    @Query("SELECT * FROM recordEntity ORDER BY id desc")
    fun getAllRecord(): List<NTDOtherRecord>


    @Insert
    fun insertRecord(record: NTDOtherRecord)
    @Update
    fun updateRecord(record: NTDOtherRecord)
    @Delete
    fun deleteRecord(record: NTDOtherRecord)

    @Query("SELECT * FROM recordEntity WHERE id = :id")
    fun getRecord(id : Int) :NTDOtherRecord

}
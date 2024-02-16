package com.ntd.appid1.appid2.appid3.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\'J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\r"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/database/NTDOtherRecordDAO;", "", "deleteRecord", "", "record", "Lcom/ntd/appid1/appid2/appid3/database/NTDOtherRecord;", "getAllRecord", "", "getRecord", "id", "", "insertRecord", "updateRecord", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public abstract interface NTDOtherRecordDAO {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM recordEntity ORDER BY id desc")
    public abstract java.util.List<com.ntd.appid1.appid2.appid3.database.NTDOtherRecord> getAllRecord();
    
    @androidx.room.Insert()
    public abstract void insertRecord(@org.jetbrains.annotations.NotNull()
    com.ntd.appid1.appid2.appid3.database.NTDOtherRecord record);
    
    @androidx.room.Update()
    public abstract void updateRecord(@org.jetbrains.annotations.NotNull()
    com.ntd.appid1.appid2.appid3.database.NTDOtherRecord record);
    
    @androidx.room.Delete()
    public abstract void deleteRecord(@org.jetbrains.annotations.NotNull()
    com.ntd.appid1.appid2.appid3.database.NTDOtherRecord record);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM recordEntity WHERE id = :id")
    public abstract com.ntd.appid1.appid2.appid3.database.NTDOtherRecord getRecord(int id);
}
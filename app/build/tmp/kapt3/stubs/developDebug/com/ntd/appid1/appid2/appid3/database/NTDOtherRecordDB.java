package com.ntd.appid1.appid2.appid3.database;

import java.lang.System;

@androidx.room.Database(entities = {com.ntd.appid1.appid2.appid3.database.NTDOtherRecord.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/database/NTDOtherRecordDB;", "Landroidx/room/RoomDatabase;", "()V", "recordDAO", "Lcom/ntd/appid1/appid2/appid3/database/NTDOtherRecordDAO;", "Companion", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public abstract class NTDOtherRecordDB extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDB.Companion Companion = null;
    @kotlin.jvm.Volatile()
    private static volatile com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDB instance;
    private static final java.lang.String DATABASE_NAME = "record.db";
    
    public NTDOtherRecordDB() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDAO recordDAO();
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/database/NTDOtherRecordDB$Companion;", "", "()V", "DATABASE_NAME", "", "instance", "Lcom/ntd/appid1/appid2/appid3/database/NTDOtherRecordDB;", "buildDatabase", "context", "Landroid/content/Context;", "getInstance", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDB getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private final com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDB buildDatabase(android.content.Context context) {
            return null;
        }
    }
}
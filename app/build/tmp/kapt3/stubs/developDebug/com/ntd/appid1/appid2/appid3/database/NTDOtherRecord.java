package com.ntd.appid1.appid2.appid3.database;

import java.lang.System;

@androidx.room.Entity(tableName = "recordEntity")
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nR\u001e\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001a"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/database/NTDOtherRecord;", "Ljava/io/Serializable;", "id", "", "fileName", "", "filePath", "durationTime", "", "createdAt", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;JJ)V", "getCreatedAt", "()J", "setCreatedAt", "(J)V", "getDurationTime", "setDurationTime", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "getFilePath", "setFilePath", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class NTDOtherRecord implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable()
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final java.lang.Integer id = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "file_name")
    private java.lang.String fileName;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "file_path")
    private java.lang.String filePath;
    @androidx.room.ColumnInfo(name = "duration_time")
    private long durationTime;
    @androidx.room.ColumnInfo(name = "created_at")
    private long createdAt;
    
    public NTDOtherRecord(@org.jetbrains.annotations.Nullable()
    java.lang.Integer id, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName, @org.jetbrains.annotations.NotNull()
    java.lang.String filePath, long durationTime, long createdAt) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFileName() {
        return null;
    }
    
    public final void setFileName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFilePath() {
        return null;
    }
    
    public final void setFilePath(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getDurationTime() {
        return 0L;
    }
    
    public final void setDurationTime(long p0) {
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final void setCreatedAt(long p0) {
    }
}
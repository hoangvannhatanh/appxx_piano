package com.ntd.appid1.appid2.appid3.screens.piano;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t\u00a8\u0006\r"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/screens/piano/NTDOtherPianoHelper;", "", "()V", "checkPermissionRecordAudio", "", "context", "Landroid/content/Context;", "checkPermissionStorage", "getSongs", "", "Lcom/ntd/appid1/appid2/appid3/model/NTDModelSong;", "getStyles", "Lcom/ntd/appid1/appid2/appid3/model/NTDPiano;", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class NTDOtherPianoHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.ntd.appid1.appid2.appid3.screens.piano.NTDOtherPianoHelper INSTANCE = null;
    
    private NTDOtherPianoHelper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.ntd.appid1.appid2.appid3.model.NTDModelSong> getSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.ntd.appid1.appid2.appid3.model.NTDPiano> getStyles() {
        return null;
    }
    
    public final boolean checkPermissionStorage(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean checkPermissionRecordAudio(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
}
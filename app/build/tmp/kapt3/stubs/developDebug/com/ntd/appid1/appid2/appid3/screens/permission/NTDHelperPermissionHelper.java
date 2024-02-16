package com.ntd.appid1.appid2.appid3.screens.permission;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J$\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/screens/permission/NTDHelperPermissionHelper;", "", "()V", "NOTIFICATION_ACCESS", "", "RECORD_AUDIO_PERMISSION_CODE", "STORAGE_PERMISSION_CODE", "checkPermissionRecordAudio", "", "context", "Landroid/content/Context;", "image", "Landroid/widget/ImageView;", "checkPermissionStorage", "requestPermissionRecordAudio", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "requestPermissionStorage", "storageActivityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class NTDHelperPermissionHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.ntd.appid1.appid2.appid3.screens.permission.NTDHelperPermissionHelper INSTANCE = null;
    public static final int RECORD_AUDIO_PERMISSION_CODE = 79;
    public static final int STORAGE_PERMISSION_CODE = 2003;
    public static final int NOTIFICATION_ACCESS = 1999;
    
    private NTDHelperPermissionHelper() {
        super();
    }
    
    public final void requestPermissionRecordAudio(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity) {
    }
    
    public final void requestPermissionStorage(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity, @org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResultLauncher<java.lang.String> storageActivityResultLauncher) {
    }
    
    public final boolean checkPermissionStorage(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView image) {
        return false;
    }
    
    public final boolean checkPermissionRecordAudio(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView image) {
        return false;
    }
}
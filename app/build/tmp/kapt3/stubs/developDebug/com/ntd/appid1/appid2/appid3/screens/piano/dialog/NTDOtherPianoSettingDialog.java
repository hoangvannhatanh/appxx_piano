package com.ntd.appid1.appid2.appid3.screens.piano.dialog;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\r\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0014J\u0012\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/screens/piano/dialog/NTDOtherPianoSettingDialog;", "Lcom/ntd/appid1/appid2/appid3/base/NTDOtherBaseDialogAppntd;", "Lcom/ntd/appid1/appid2/appid3/databinding/DialogPianoSettingBinding;", "ctx", "Landroid/content/Context;", "onSettingSaved", "Lkotlin/Function0;", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "audioManager", "Landroid/media/AudioManager;", "receiver", "Lcom/ntd/appid1/appid2/appid3/screens/piano/dialog/NTDOtherPianoSettingDialog$MediaButtonReceiver;", "initView", "context", "setOnDismissListener", "listener", "Landroid/content/DialogInterface$OnDismissListener;", "setViewBinding", "MediaButtonReceiver", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class NTDOtherPianoSettingDialog extends com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd<com.ntd.appid1.appid2.appid3.databinding.DialogPianoSettingBinding> {
    private final kotlin.jvm.functions.Function0<kotlin.Unit> onSettingSaved = null;
    private android.media.AudioManager audioManager;
    private com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherPianoSettingDialog.MediaButtonReceiver receiver;
    private android.content.Context ctx;
    
    public NTDOtherPianoSettingDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context ctx, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSettingSaved) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected com.ntd.appid1.appid2.appid3.databinding.DialogPianoSettingBinding setViewBinding(@org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        return null;
    }
    
    @java.lang.Override()
    protected void initView(@org.jetbrains.annotations.Nullable()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void setOnDismissListener(@org.jetbrains.annotations.Nullable()
    android.content.DialogInterface.OnDismissListener listener) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\t"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/screens/piano/dialog/NTDOtherPianoSettingDialog$MediaButtonReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/ntd/appid1/appid2/appid3/screens/piano/dialog/NTDOtherPianoSettingDialog;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
    public final class MediaButtonReceiver extends android.content.BroadcastReceiver {
        
        public MediaButtonReceiver() {
            super();
        }
        
        @java.lang.Override()
        public void onReceive(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        android.content.Intent intent) {
        }
    }
}
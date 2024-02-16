package com.ntd.appid1.appid2.appid3.screens.drum;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\b\u0010\u001c\u001a\u00020\u0018H\u0002J\b\u0010\u001d\u001a\u00020\u0018H\u0014J\b\u0010\u001e\u001a\u00020\u0018H\u0014J\b\u0010\u001f\u001a\u00020\u0018H\u0014J\b\u0010 \u001a\u00020\u0018H\u0003J\b\u0010!\u001a\u00020\u0018H\u0014J\b\u0010\"\u001a\u00020\u0018H\u0016J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u000fH\u0016J\b\u0010%\u001a\u00020\u0002H\u0014J\b\u0010&\u001a\u00020\u0018H\u0002J\b\u0010\'\u001a\u00020\u0018H\u0002J\b\u0010(\u001a\u00020\u0018H\u0002J\b\u0010)\u001a\u00020\u0018H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/screens/drum/NTDViewDrumeActivity;", "Lcom/ntd/appid1/appid2/appid3/base/NTDOtherBaseActivityAppntd;", "Lcom/ntd/appid1/appid2/appid3/databinding/ActivityDrumeBinding;", "Lcom/ntd/appid1/appid2/appid3/screens/saxophone/TimerListener;", "()V", "dialogRecordSuccess", "Landroid/app/Dialog;", "dialogSaveRecord", "Lcom/ntd/appid1/appid2/appid3/base/NTDOtherBaseDialogAppntd;", "Lcom/ntd/appid1/appid2/appid3/databinding/DialogSaveRecordBinding;", "drum1Fragment", "Lcom/ntd/appid1/appid2/appid3/screens/drum/NTDViewDrum1Fragment;", "drum2Fragment", "Lcom/ntd/appid1/appid2/appid3/screens/drum/NTDViewDrum2Fragment;", "fileName", "", "isRecording", "", "mediaPlayer", "Landroid/media/MediaPlayer;", "mediaRecorder", "Landroid/media/MediaRecorder;", "outputPath", "changeStyle", "", "style", "Landroidx/fragment/app/Fragment;", "getOutputPath", "init", "initData", "initListener", "initView", "onClick", "onResume", "onTimerFinish", "onTimerTick", "time", "setViewBinding", "startRecording", "stopRecording", "whenBackPress", "whenStopRecording", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class NTDViewDrumeActivity extends com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd<com.ntd.appid1.appid2.appid3.databinding.ActivityDrumeBinding> implements com.ntd.appid1.appid2.appid3.screens.saxophone.TimerListener {
    private android.media.MediaRecorder mediaRecorder;
    private android.media.MediaPlayer mediaPlayer;
    private java.lang.String outputPath = "";
    private java.lang.String fileName;
    private com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd<com.ntd.appid1.appid2.appid3.databinding.DialogSaveRecordBinding> dialogSaveRecord;
    private android.app.Dialog dialogRecordSuccess;
    private boolean isRecording = false;
    private final com.ntd.appid1.appid2.appid3.screens.drum.NTDViewDrum1Fragment drum1Fragment = null;
    private final com.ntd.appid1.appid2.appid3.screens.drum.NTDViewDrum2Fragment drum2Fragment = null;
    
    public NTDViewDrumeActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected com.ntd.appid1.appid2.appid3.databinding.ActivityDrumeBinding setViewBinding() {
        return null;
    }
    
    @java.lang.Override()
    protected void initView() {
    }
    
    @java.lang.Override()
    protected void initData() {
    }
    
    @java.lang.Override()
    protected void initListener() {
    }
    
    private final void init() {
    }
    
    private final void whenBackPress() {
    }
    
    private final void whenStopRecording() {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void onClick() {
    }
    
    private final void stopRecording() {
    }
    
    private final void startRecording() {
    }
    
    private final java.lang.String getOutputPath() {
        return null;
    }
    
    @java.lang.Override()
    public void onTimerTick(@org.jetbrains.annotations.NotNull()
    java.lang.String time) {
    }
    
    @java.lang.Override()
    public void onTimerFinish() {
    }
    
    private final void changeStyle(androidx.fragment.app.Fragment style) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
}
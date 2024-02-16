package com.ntd.appid1.appid2.appid3.screens.saxophone;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\nH\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0014J\b\u0010\u001d\u001a\u00020\u0013H\u0014J\b\u0010\u001e\u001a\u00020\u0013H\u0014J\b\u0010\u001f\u001a\u00020\u0013H\u0003J\b\u0010 \u001a\u00020\u0013H\u0014J\b\u0010!\u001a\u00020\u0013H\u0014J\b\u0010\"\u001a\u00020\u0013H\u0016J\u0010\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\nH\u0016J\u0010\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0017H\u0002J\u0010\u0010\'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u0017H\u0002J\b\u0010)\u001a\u00020\u0002H\u0014J\b\u0010*\u001a\u00020\u0013H\u0002J\b\u0010+\u001a\u00020\u0013H\u0002J\b\u0010,\u001a\u00020\u0013H\u0002J\b\u0010-\u001a\u00020\u0013H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/ntd/appid1/appid2/appid3/screens/saxophone/NTDViewSaxophoneActivity;", "Lcom/ntd/appid1/appid2/appid3/base/NTDOtherBaseActivityAppntd;", "Lcom/ntd/appid1/appid2/appid3/databinding/ActivitySaxophoneBinding;", "Lcom/ntd/appid1/appid2/appid3/screens/saxophone/TimerListener;", "()V", "dialogRecordSuccess", "Landroid/app/Dialog;", "dialogSaveRecord", "Lcom/ntd/appid1/appid2/appid3/screens/piano/dialog/NTDOtherSaveRecordDialog;", "fileName", "", "isRecording", "", "mediaRecorder", "Landroid/media/MediaRecorder;", "outputPath", "timeTotal", "", "changeImage", "", "imageView", "Landroid/widget/ImageView;", "imageInit", "", "imageChange", "getOutputPath", "handleTimer", "init", "initData", "initListener", "initView", "onClick", "onDestroy", "onPause", "onTimerFinish", "onTimerTick", "time", "playSound", "index", "setStatusBarGradiant", "currentTheme", "setViewBinding", "startRecording", "stopRecording", "whenBackPress", "whenStopRecording", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class NTDViewSaxophoneActivity extends com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd<com.ntd.appid1.appid2.appid3.databinding.ActivitySaxophoneBinding> implements com.ntd.appid1.appid2.appid3.screens.saxophone.TimerListener {
    private long timeTotal = 0L;
    private android.media.MediaRecorder mediaRecorder;
    private java.lang.String outputPath = "";
    private java.lang.String fileName = "";
    private boolean isRecording = false;
    private com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherSaveRecordDialog dialogSaveRecord;
    private android.app.Dialog dialogRecordSuccess;
    
    public NTDViewSaxophoneActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected com.ntd.appid1.appid2.appid3.databinding.ActivitySaxophoneBinding setViewBinding() {
        return null;
    }
    
    @java.lang.Override()
    protected void initView() {
    }
    
    private final void handleTimer() {
    }
    
    private final void whenBackPress() {
    }
    
    private final void whenStopRecording() {
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
    
    @java.lang.Override()
    protected void initData() {
    }
    
    @java.lang.Override()
    protected void initListener() {
    }
    
    private final void init() {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void onClick() {
    }
    
    private final void playSound(int index) {
    }
    
    private final void changeImage(android.widget.ImageView imageView, int imageInit, int imageChange) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    private final void setStatusBarGradiant(int currentTheme) {
    }
}
package com.ntd.appid1.appid2.appid3.screens.record;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0002J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012H\u0002J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0014J\b\u0010\u001e\u001a\u00020\u0016H\u0014J\b\u0010\u001f\u001a\u00020\u0016H\u0014J\b\u0010 \u001a\u00020\u0016H\u0016J\b\u0010!\u001a\u00020\u0016H\u0014J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u0007H\u0002J\b\u0010$\u001a\u00020\u0002H\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/screens/record/NTDViewRecordDetail;", "Lcom/ntd/appid1/appid2/appid3/base/NTDOtherBaseActivityAppntd;", "Lcom/ntd/appid1/appid2/appid3/databinding/ActivityRecordDetailBinding;", "()V", "countDownTimer", "Landroid/os/CountDownTimer;", "currentTimerPosition", "", "db", "Lcom/ntd/appid1/appid2/appid3/database/NTDOtherRecordDB;", "dialogChangeName", "Landroid/app/Dialog;", "dialogDeleteRecord", "handler", "Landroid/os/Handler;", "isStarted", "", "mItem", "Lcom/ntd/appid1/appid2/appid3/database/NTDOtherRecord;", "mediaPlayer", "Landroid/media/MediaPlayer;", "changeNewName", "", "item", "convertLongToFormattedTime", "", "time", "deleteRecord", "handlePlayMusicFile", "initData", "initListener", "initView", "onBackPressed", "onStop", "setCountDownTimer", "duration", "setViewBinding", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class NTDViewRecordDetail extends com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd<com.ntd.appid1.appid2.appid3.databinding.ActivityRecordDetailBinding> {
    private android.app.Dialog dialogChangeName;
    private android.app.Dialog dialogDeleteRecord;
    private android.media.MediaPlayer mediaPlayer;
    private com.ntd.appid1.appid2.appid3.database.NTDOtherRecord mItem;
    private android.os.CountDownTimer countDownTimer;
    private long currentTimerPosition = 0L;
    private final com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDB db = null;
    private boolean isStarted = false;
    private android.os.Handler handler;
    
    public NTDViewRecordDetail() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected com.ntd.appid1.appid2.appid3.databinding.ActivityRecordDetailBinding setViewBinding() {
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
    
    private final void handlePlayMusicFile(com.ntd.appid1.appid2.appid3.database.NTDOtherRecord item) {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    private final void setCountDownTimer(long duration) {
    }
    
    private final void changeNewName(com.ntd.appid1.appid2.appid3.database.NTDOtherRecord item) {
    }
    
    private final void deleteRecord(com.ntd.appid1.appid2.appid3.database.NTDOtherRecord item) {
    }
    
    private final java.lang.String convertLongToFormattedTime(long time) {
        return null;
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
}
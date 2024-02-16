package com.ntd.appid1.appid2.appid3.screens.saxophone;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\rJ\u0006\u0010\t\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/screens/saxophone/TimerManager;", "", "()V", "countDownTimer", "Landroid/os/CountDownTimer;", "elapsedTimeInSeconds", "", "isTimeCounting", "", "timeTotal", "timerListener", "Lcom/ntd/appid1/appid2/appid3/screens/saxophone/TimerListener;", "releaseTimer", "", "setTimeCounting", "value", "startTimer", "listener", "stopTimer", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class TimerManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.ntd.appid1.appid2.appid3.screens.saxophone.TimerManager INSTANCE = null;
    private static android.os.CountDownTimer countDownTimer;
    private static com.ntd.appid1.appid2.appid3.screens.saxophone.TimerListener timerListener;
    private static long elapsedTimeInSeconds = 0L;
    private static long timeTotal = 0L;
    private static boolean isTimeCounting = false;
    
    private TimerManager() {
        super();
    }
    
    public final void startTimer(@org.jetbrains.annotations.NotNull()
    com.ntd.appid1.appid2.appid3.screens.saxophone.TimerListener listener) {
    }
    
    public final void stopTimer() {
    }
    
    public final void releaseTimer() {
    }
    
    public final boolean isTimeCounting() {
        return false;
    }
    
    public final long timeTotal() {
        return 0L;
    }
    
    public final void setTimeCounting(boolean value) {
    }
}
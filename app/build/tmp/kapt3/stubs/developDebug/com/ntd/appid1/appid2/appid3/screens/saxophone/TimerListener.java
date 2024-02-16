package com.ntd.appid1.appid2.appid3.screens.saxophone;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/screens/saxophone/TimerListener;", "", "onTimerFinish", "", "onTimerTick", "time", "", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public abstract interface TimerListener {
    
    public abstract void onTimerTick(@org.jetbrains.annotations.NotNull()
    java.lang.String time);
    
    public abstract void onTimerFinish();
}
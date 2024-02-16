package com.ntd.appid1.appid2.appid3;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\u000bH\u0014J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\tH\u0002J\b\u0010\u000f\u001a\u00020\u000bH\u0014J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0014J\b\u0010\u0012\u001a\u00020\u0002H\u0014J\b\u0010\u0013\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/NTDViewMainActivity;", "Lcom/ntd/appid1/appid2/appid3/base/NTDOtherBaseActivityAppntd;", "Lcom/ntd/appid1/appid2/appid3/databinding/ActivityMainBinding;", "()V", "pagerAdapter", "Lcom/ntd/appid1/appid2/appid3/adapter/NTDOtherViewPagerAdapter;", "ratingDialog", "Lcom/ntd/appid1/appid2/appid3/screens/rate/NTDOtherRatingDialog;", "checkPermission", "", "initData", "", "initListener", "initRateDialog", "isQuitApp", "initView", "onBackPressed", "onResume", "setViewBinding", "setViewPager", "Companion", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
public final class NTDViewMainActivity extends com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd<com.ntd.appid1.appid2.appid3.databinding.ActivityMainBinding> {
    private com.ntd.appid1.appid2.appid3.adapter.NTDOtherViewPagerAdapter pagerAdapter;
    private com.ntd.appid1.appid2.appid3.screens.rate.NTDOtherRatingDialog ratingDialog;
    @org.jetbrains.annotations.NotNull()
    public static final com.ntd.appid1.appid2.appid3.NTDViewMainActivity.Companion Companion = null;
    private static boolean showRateFromInstrument = false;
    
    public NTDViewMainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected com.ntd.appid1.appid2.appid3.databinding.ActivityMainBinding setViewBinding() {
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
    
    private final void setViewPager() {
    }
    
    private final boolean checkPermission() {
        return false;
    }
    
    private final void initRateDialog(boolean isQuitApp) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/ntd/appid1/appid2/appid3/NTDViewMainActivity$Companion;", "", "()V", "showRateFromInstrument", "", "getShowRateFromInstrument", "()Z", "setShowRateFromInstrument", "(Z)V", "AppXX_LearnPiano_v1.0.0_v100_02.16.2024_developDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean getShowRateFromInstrument() {
            return false;
        }
        
        public final void setShowRateFromInstrument(boolean p0) {
        }
    }
}
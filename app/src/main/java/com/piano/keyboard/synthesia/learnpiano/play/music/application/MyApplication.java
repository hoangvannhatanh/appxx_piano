package com.piano.keyboard.synthesia.learnpiano.play.music.application;

import com.appsflyer.adrevenue.BuildConfig;
import com.nlbn.ads.util.AdsApplication;
import com.nlbn.ads.util.AppOpenManager;
import com.nlbn.ads.util.AppsFlyer;
import com.piano.keyboard.synthesia.learnpiano.play.music.R;
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.splash.NTDViewSplashActivity;

import java.util.List;

public class MyApplication extends AdsApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        AppOpenManager.getInstance().disableAppResumeWithActivity(NTDViewSplashActivity.class);
        AppsFlyer.getInstance().initAppFlyer(this, getString(R.string.app_flyer), true);
    }

    @Override
    public boolean enableAdsResume() {
        return true;
    }

    @Override
    public List<String> getListTestDeviceId() {
        return null;
    }

    @Override
    public String getResumeAdId() {
        return getString(R.string.appopen_resume);
    }

    @Override
    public Boolean buildDebug() {
        return BuildConfig.DEBUG;
    }
}
package com.piano.keyboard.synthesia.learnpiano.play.music.ads_config;

import android.content.Context;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.nlbn.ads.callback.InterCallback;
import com.nlbn.ads.util.Admob;
import com.piano.keyboard.synthesia.learnpiano.play.music.R;

public class AdsConfig {

    private static AdsConfig instance = null;
    public InterstitialAd mInterstitialAdPiano;
    public InterstitialAd mInterstitialAdAll;
    public InterstitialAd mInterstitialAdStyle;

    private AdsConfig() {

    }

    public static synchronized AdsConfig getAdsConfig() {
        if (instance == null) {
            instance = new AdsConfig();
        }
        return instance;
    }

    public void loadAdsInterPiano(Context context) {
        Admob.getInstance().loadInterAds(context, context.getString(R.string.inter_piano), new InterCallback() {
            @Override
            public void onInterstitialLoad(InterstitialAd interstitialAd) {
                super.onInterstitialLoad(interstitialAd);
                mInterstitialAdPiano = interstitialAd;
            }
        });
    }

    public void loadAdsInterAll(Context context) {
        Admob.getInstance().loadInterAds(context, context.getString(R.string.inter_all), new InterCallback() {
            @Override
            public void onInterstitialLoad(InterstitialAd interstitialAd) {
                super.onInterstitialLoad(interstitialAd);
                mInterstitialAdAll = interstitialAd;
            }
        });
    }

    public void loadAdsInterStyle(Context context) {
        Admob.getInstance().loadInterAds(context, context.getString(R.string.inter_style), new InterCallback() {
            @Override
            public void onInterstitialLoad(InterstitialAd interstitialAd) {
                super.onInterstitialLoad(interstitialAd);
                mInterstitialAdStyle = interstitialAd;
            }
        });
    }

}
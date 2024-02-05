package com.piano.keyboard.synthesia.learnpiano.play.music

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.nlbn.ads.callback.InterCallback
import com.nlbn.ads.callback.NativeCallback
import com.nlbn.ads.util.Admob
import com.piano.keyboard.synthesia.learnpiano.play.music.ads_config.AdsConfig
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseFragmentAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.FragmentHomeBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.extensions.onAvoidDoubleClick
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.drum.NTDViewDrumeActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.guitar.NTDViewGuitarActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.piano.NTDViewPianoActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.saxophone.NTDViewSaxophoneActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.Network
import java.util.Timer
import java.util.TimerTask

class NTDViewHomeFragment : NTDOtherBaseFragmentAppntd<FragmentHomeBinding>() {
    override fun initView() {
        loadNative()

        //inter ads
        if (AdsConfig.getAdsConfig().mInterstitialAdPiano == null) {
            AdsConfig.getAdsConfig().loadAdsInterPiano(requireActivity())
        }
    }

    override fun setViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, viewGroup, false)
    }

    override fun viewListener() {
        binding.layoutPiano.onAvoidDoubleClick {
            handleInterAds()
        }

        binding.layoutGuitar.onAvoidDoubleClick {
            startActivity(Intent(requireActivity(), NTDViewGuitarActivity::class.java))
        }

        binding.layoutDrumset.onAvoidDoubleClick {
            startActivity(Intent(requireActivity(), NTDViewDrumeActivity::class.java))
        }

        binding.layoutSaxophone.onAvoidDoubleClick {
            startActivity(Intent(requireActivity(), NTDViewSaxophoneActivity::class.java))
        }
    }

    private fun handleInterAds() {
        val timer = Timer()
        val adsTask: TimerTask = object : TimerTask() {
            override fun run() {
                enableShowInterAds = true
                Log.d("handleInterAds==", "enableShowInterAds = true")
            }
        }

        if (Network().isNetworkAvailable(requireActivity())) {
            if (fistItemClick || enableShowInterAds) {
                Admob.getInstance().showInterAds(
                    requireActivity(),
                    AdsConfig.getAdsConfig().mInterstitialAdPiano,
                    object : InterCallback() {
                        override fun onNextAction() {
                            super.onNextAction()
                            AdsConfig.getAdsConfig()
                                .loadAdsInterPiano(requireActivity())
                        }

                        override fun onAdClosedByUser() {
                            super.onAdClosedByUser()

                            startActivity(Intent(requireActivity(), NTDViewPianoActivity::class.java))
                            enableShowInterAds = false
                            fistItemClick = false
                            timer.schedule(adsTask, 15000L)
                            Log.d("handleInterAds==", "onAdClosedByUser called")
                        }
                    })
            } else {
                startActivity(Intent(requireActivity(), NTDViewPianoActivity::class.java))
            }
        } else {
            startActivity(Intent(requireActivity(), NTDViewPianoActivity::class.java))
        }
    }

    private fun loadNative() {
        if (Network().isNetworkAvailable(requireActivity())) {
            try {
                Admob.getInstance().loadNativeAd(
                    requireActivity(),
                    getString(R.string.native_home),
                    object : NativeCallback() {
                        override fun onNativeAdLoaded(nativeAd: NativeAd) {
                            super.onNativeAdLoaded(nativeAd)
                            val adView = LayoutInflater.from(requireActivity()).inflate(
                                R.layout.native_ads_small_below_button,
                                null
                            ) as NativeAdView
                            binding.nativeAds.removeAllViews()
                            binding.nativeAds.addView(adView)
                            Admob.getInstance().pushAdsToViewCustom(nativeAd, adView)
                        }

                        override fun onAdFailedToLoad() {
                            super.onAdFailedToLoad()
                            binding.nativeAds.removeAllViews()
                        }
                    })
            } catch (exception: java.lang.Exception) {
                binding.nativeAds.removeAllViews()
            }
        } else {
            binding.nativeAds.removeAllViews()
        }
    }

    companion object {
        var enableShowInterAds: Boolean = false
        var fistItemClick: Boolean = true
    }
}
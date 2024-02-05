package com.piano.keyboard.synthesia.learnpiano.play.music.screens.language

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.nlbn.ads.callback.NativeCallback
import com.nlbn.ads.util.Admob
import com.piano.keyboard.synthesia.learnpiano.play.music.NTDViewMainActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.adapter.NTDOtherLanguageAdapter
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseActivityAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperPreferencesHelper
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperSharePrefUtils
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.LanguageActivityBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.extensions.onAvoidDoubleClick
import com.piano.keyboard.synthesia.learnpiano.play.music.model.NTDModelLanguage
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.intro.NTDViewIntroActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.NTDOtherUtils
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.Network
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NTDViewLanguageActivity : NTDOtherBaseActivityAppntd<LanguageActivityBinding>() {
    private var languageCode = ""
    private var adapter: NTDOtherLanguageAdapter? = null
    private var listLanguage = arrayListOf<NTDModelLanguage>()
    private var isOpenFirst: Boolean = false
    override fun setViewBinding(): LanguageActivityBinding {
        return LanguageActivityBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        isOpenFirst = NTDHelperSharePrefUtils.getBoolean(
            NTDHelperSharePrefUtils.SHARE_PREF_OPEN_FIRST_APP, true)

        //ads
        if (isOpenFirst) loadNativeLanguageStart()
        else loadNativeLanguageSetting()

        if (isOpenFirst) {
            binding.imgBack.visibility = View.GONE
        } else binding.imgBack.visibility = View.VISIBLE
        languageCode = NTDHelperPreferencesHelper.getLanguage()
        listLanguage.add(NTDModelLanguage("English", "en", false, R.drawable.ic_english))
        listLanguage.add(NTDModelLanguage("Portuguese", "pt", false, R.drawable.ic_portugal))
        listLanguage.add(NTDModelLanguage("French", "fr", false, R.drawable.ic_france))
        listLanguage.add(NTDModelLanguage("Spanish", "es", false, R.drawable.ic_spanish))
        listLanguage.add(NTDModelLanguage("Hindi", "hi", false, R.drawable.ic_india))
        binding.rvLanguage.layoutManager = LinearLayoutManager(this)
        adapter = NTDOtherLanguageAdapter(this) {
            languageCode = it.isoLanguage
        }
        binding.rvLanguage.adapter = adapter
        adapter?.setData(listLanguage)
        val position =
            if (listLanguage.indexOfFirst { it.isoLanguage == NTDHelperPreferencesHelper.getLanguage() } == -1) {
                0
            } else {
                listLanguage.indexOfFirst { it.isoLanguage == NTDHelperPreferencesHelper.getLanguage() }
            }
        adapter?.setCurrentPosition(position)
        binding.imgDone.onAvoidDoubleClick {
            NTDHelperPreferencesHelper.setLanguage(languageCode)
            NTDOtherUtils.setLocale(this, languageCode)
            if (isOpenFirst) {
                startActivity(Intent(this@NTDViewLanguageActivity, NTDViewIntroActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@NTDViewLanguageActivity, NTDViewMainActivity::class.java))
                finishAffinity()
            }

        }
        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    private fun loadNativeLanguageStart() {
        if (Network().isNetworkAvailable(this)) {
            try {
                Admob.getInstance().loadNativeAd(
                    this,
                    getString(R.string.native_language),
                    object : NativeCallback() {
                        override fun onNativeAdLoaded(nativeAd: NativeAd) {
                            super.onNativeAdLoaded(nativeAd)
                            val adView = LayoutInflater.from(this@NTDViewLanguageActivity)
                                .inflate(R.layout.native_ads_below_button, null) as NativeAdView
                            binding.nativeAds.removeAllViews()
                            binding.nativeAds.addView(adView)
                            Admob.getInstance().pushAdsToViewCustom(nativeAd, adView)
                        }

                        override fun onAdFailedToLoad() {
                            super.onAdFailedToLoad()
                            binding.nativeAds.removeAllViews()
                        }
                    })
            } catch (exception: Exception) {
                binding.nativeAds.removeAllViews()
            }
        } else {
            binding.nativeAds.removeAllViews()
        }
    }

    private fun loadNativeLanguageSetting() {
        if (Network().isNetworkAvailable(this)) {
            try {
                Admob.getInstance().loadNativeAd(
                    this,
                    getString(R.string.native_language_setting),
                    object : NativeCallback() {
                        override fun onNativeAdLoaded(nativeAd: NativeAd) {
                            super.onNativeAdLoaded(nativeAd)
                            val adView = LayoutInflater.from(this@NTDViewLanguageActivity)
                                .inflate(R.layout.native_ads_below_button, null) as NativeAdView
                            binding.nativeAds.removeAllViews()
                            binding.nativeAds.addView(adView)
                            Admob.getInstance().pushAdsToViewCustom(nativeAd, adView)
                        }

                        override fun onAdFailedToLoad() {
                            super.onAdFailedToLoad()
                            binding.nativeAds.removeAllViews()
                        }
                    })
            } catch (exception: Exception) {
                binding.nativeAds.removeAllViews()
            }
        } else {
            binding.nativeAds.removeAllViews()
        }
    }

    override fun initData() {}

    override fun initListener() {}
}
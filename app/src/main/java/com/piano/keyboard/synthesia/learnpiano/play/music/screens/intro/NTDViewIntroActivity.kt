package com.piano.keyboard.synthesia.learnpiano.play.music.screens.intro

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.nlbn.ads.callback.NativeCallback
import com.nlbn.ads.util.Admob
import com.piano.keyboard.synthesia.learnpiano.play.music.NTDViewMainActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.adapter.NTDOtherIntroAdapter
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseActivityAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperSharePrefUtils
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ActivityIntroBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.extensions.onAvoidDoubleClick
import com.piano.keyboard.synthesia.learnpiano.play.music.model.NTDModelIntro
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.permission.NTDViewPermissionActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.Network

class NTDViewIntroActivity : NTDOtherBaseActivityAppntd<ActivityIntroBinding>() {

    private lateinit var introAdapter: NTDOtherIntroAdapter
    private var isOpenFirst: Boolean = false
    override fun setViewBinding(): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        isOpenFirst = NTDHelperSharePrefUtils.getBoolean(
            NTDHelperSharePrefUtils.SHARE_PREF_OPEN_FIRST_APP,true)
        init()
        onClick()
    }

    override fun initData() {}

    override fun initListener() {}

    fun init() {
        introAdapter = NTDOtherIntroAdapter(this@NTDViewIntroActivity)
        introAdapter.setData(mutableListOf<NTDModelIntro>().apply {
            add(
                NTDModelIntro(getString(R.string.title_intro_1),
                    getString(R.string.content_intro_1),
                    R.drawable.img_intro1)
            )
            add(
                NTDModelIntro(getString(R.string.title_intro_2),
                    getString(R.string.content_intro_2),
                    R.drawable.img_intro2)
            )
            add(
                NTDModelIntro(getString(R.string.title_intro_3),
                    getString(R.string.content_intro_3),
                    R.drawable.img_intro3)
            )
        })
        binding.layoutContainer.adapter = introAdapter
        binding.layoutContainer.currentItem = 0
        binding.layoutContainer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateUIByPageChanged(position)
            }
        })

    }

    fun updateUIByPageChanged(position: Int) {
        when (position) {
            0 -> {
                binding.tvTitle.text = getString(R.string.title_intro_1)
                binding.tvContent.text = getString(R.string.content_intro_1)
                binding.ivDot.setImageResource(R.drawable.bg_dot_1)
                binding.textNext.text = getString(R.string.next)
                loadNative(R.string.native_intro1)
            }
            1 -> {
                binding.tvTitle.text = getString(R.string.title_intro_2)
                binding.tvContent.text = getString(R.string.content_intro_2)
                binding.ivDot.setImageResource(R.drawable.bg_dot_2)
                binding.textNext.text = getString(R.string.next)
                loadNative(R.string.native_intro2)
            }
            2 -> {
                binding.tvTitle.text = getString(R.string.title_intro_3)
                binding.tvContent.text = getString(R.string.content_intro_3)
                binding.ivDot.setImageResource(R.drawable.bg_dot_3)
                binding.textNext.text = getString(R.string.start)
                loadNative(R.string.native_intro3)
            }
        }
    }

    fun onClick() {
        binding.textNext.onAvoidDoubleClick {
            var index: Int = binding.layoutContainer.currentItem
            updateUIByPageChanged(index)
            if (index < 2) {
                ++index
                binding.layoutContainer.currentItem = index
            } else {
                if (isOpenFirst) {
                    startActivity(Intent(this, NTDViewPermissionActivity::class.java))
                } else {
                    NTDHelperSharePrefUtils.putBoolean(
                        NTDHelperSharePrefUtils.SHARE_PREF_OPEN_FIRST_APP, false)
                    startActivity(Intent(this, NTDViewMainActivity::class.java))
                }
                finish()
            }
            binding.textNext.text = if (index == 2) {
                resources.getString(R.string.start)
            } else {
                resources.getString(R.string.next)
            }
        }
    }

    private fun loadNative(idAds : Int) {
        if (Network().isNetworkAvailable(this)) {
            try {
                Admob.getInstance().loadNativeAd(
                    this,
                    getString(idAds),
                    object : NativeCallback() {
                        override fun onNativeAdLoaded(nativeAd: NativeAd) {
                            super.onNativeAdLoaded(nativeAd)
                            val adView = LayoutInflater.from(this@NTDViewIntroActivity)
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
            binding.nativeAds.visibility = View.INVISIBLE
        }
    }
}
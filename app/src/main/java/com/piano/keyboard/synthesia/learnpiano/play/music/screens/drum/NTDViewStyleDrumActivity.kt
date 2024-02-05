package com.piano.keyboard.synthesia.learnpiano.play.music.screens.drum

import android.view.LayoutInflater
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.nlbn.ads.callback.InterCallback
import com.nlbn.ads.util.Admob
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.ads_config.AdsConfig
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseActivityAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ActivityStyleDrumBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperSharePrefUtils
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.Network
import java.util.LinkedList
import java.util.Timer
import java.util.TimerTask
import kotlin.math.abs

class NTDViewStyleDrumActivity : NTDOtherBaseActivityAppntd<ActivityStyleDrumBinding>() {
    private var listStyle: LinkedList<Int> = LinkedList()
    private var imageSlideAdapter: NTDOtherImageSlideAdapter? = null
    private var compositePageTransformer: CompositePageTransformer? = null

    override fun setViewBinding(): ActivityStyleDrumBinding {
        return ActivityStyleDrumBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        listStyle.add(R.drawable.style1_drum)
        listStyle.add(R.drawable.style2_drum)

        binding.vp2.offscreenPageLimit = 3
        binding.vp2.clipToPadding = false
        binding.vp2.clipChildren = false

        compositePageTransformer = CompositePageTransformer()
        compositePageTransformer?.addTransformer(MarginPageTransformer(40))
        compositePageTransformer?.addTransformer(ViewPager2.PageTransformer { page, position ->
            page.scaleY= .85f + (1 - abs(position))*.15f
        })

        binding.vp2.setPageTransformer(compositePageTransformer)

        imageSlideAdapter = NTDOtherImageSlideAdapter(listStyle, this)
        binding.vp2.adapter = imageSlideAdapter

        //inter ads
        if (AdsConfig.getAdsConfig().mInterstitialAdStyle == null) {
            AdsConfig.getAdsConfig().loadAdsInterStyle(this)
        }
    }

    override fun initData() {
    }

    override fun initListener() {
        binding.icCheck.setOnClickListener {
            NTDHelperSharePrefUtils.setStyleDrum(binding.vp2.currentItem)
            handleInterAds()
        }
        binding.icBack.setOnClickListener {
            finish()
        }
        binding.vp2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> {
                        binding.icDot1.isSelected = true
                        binding.icDot2.isSelected = false
                    }
                    1 -> {
                        binding.icDot1.isSelected = false
                        binding.icDot2.isSelected = true
                    }
                }
            }
        })
    }

    private fun handleInterAds() {
        val timer = Timer()
        val adsTask: TimerTask = object : TimerTask() {
            override fun run() {
                enableShowInterAds = true
            }
        }

        if (Network().isNetworkAvailable(this@NTDViewStyleDrumActivity)) {
            if (fistItemClick || enableShowInterAds) {
                Admob.getInstance().showInterAds(
                    this@NTDViewStyleDrumActivity,
                    AdsConfig.getAdsConfig().mInterstitialAdStyle,
                    object : InterCallback() {
                        override fun onNextAction() {
                            super.onNextAction()
                            AdsConfig.getAdsConfig()
                                .loadAdsInterStyle(this@NTDViewStyleDrumActivity)
                            finish()
                            enableShowInterAds = false
                            fistItemClick = false
                            timer.schedule(adsTask, 15000)
                        }
                    })
            } else {
                finish()
            }
        } else {
            finish()
        }
    }

    companion object {
        var enableShowInterAds: Boolean = false
        var fistItemClick: Boolean = true
    }
}
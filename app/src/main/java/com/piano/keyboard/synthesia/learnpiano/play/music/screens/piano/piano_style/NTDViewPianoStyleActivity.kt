package com.piano.keyboard.synthesia.learnpiano.play.music.screens.piano.piano_style

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.nlbn.ads.callback.InterCallback
import com.nlbn.ads.util.Admob
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.ads_config.AdsConfig
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseActivityAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ActivityNtdviewPianoStyleBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperSharePrefUtils
import com.piano.keyboard.synthesia.learnpiano.play.music.model.NTDPianoStyle
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.piano.NTDViewPianoActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.Constants
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.Network
import java.util.Timer
import java.util.TimerTask
import kotlin.math.abs

class NTDViewPianoStyleActivity : NTDOtherBaseActivityAppntd<ActivityNtdviewPianoStyleBinding>() {
    private var listPianoStyles: MutableList<NTDPianoStyle> = mutableListOf()
    private lateinit var pianoStyleAdapter: NTDPianoStyleAdapter
    override fun setViewBinding(): ActivityNtdviewPianoStyleBinding {
        return ActivityNtdviewPianoStyleBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        listPianoStyles = getPianoStyles()
        pianoStyleAdapter = NTDPianoStyleAdapter(listPianoStyles)
        binding.vpPianoStyle.apply {
            adapter = pianoStyleAdapter
            offscreenPageLimit = 3
            clipChildren = false
            clipToPadding = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(0))
            transformer.addTransformer { page, position ->
                page.scaleY = 0.85f + (1 - abs(position)) * 0.15f
            }
            setPageTransformer(transformer)

            currentItem = NTDHelperSharePrefUtils.getInt(NTDHelperSharePrefUtils.STYLE_PIANO, 0)
        }

        setItemSelected(binding.vpPianoStyle.currentItem)

        //inter ads
        if (AdsConfig.getAdsConfig().mInterstitialAdStyle == null) {
            AdsConfig.getAdsConfig().loadAdsInterStyle(this)
        }
    }

    override fun initData() {

    }

    override fun initListener() {
        binding.vpPianoStyle.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setItemSelected(position)
            }
        })

        binding.icBack.setOnClickListener {
            finish()
        }

        binding.icDone.setOnClickListener {
            NTDHelperSharePrefUtils.putInt(NTDHelperSharePrefUtils.STYLE_PIANO, binding.vpPianoStyle.currentItem)
            handleInterAds()
        }
    }

    private fun getPianoStyles(): MutableList<NTDPianoStyle> {
        val list: MutableList<NTDPianoStyle> = mutableListOf()
        list.add(NTDPianoStyle(Constants.PIANO_STYLE_DEFAULT, R.drawable.img_piano_style_df, false))
        list.add(NTDPianoStyle(Constants.PIANO_STYLE_1, R.drawable.img_piano_style1, false))
        list.add(NTDPianoStyle(Constants.PIANO_STYLE_2, R.drawable.img_piano_style2, false))
        list.add(NTDPianoStyle(Constants.PIANO_STYLE_3, R.drawable.img_piano_style3, false))
        list.add(NTDPianoStyle(Constants.PIANO_STYLE_4, R.drawable.img_piano_style4, false))
        list.add(NTDPianoStyle(Constants.PIANO_STYLE_5, R.drawable.img_piano_style5, false))
        list.add(NTDPianoStyle(Constants.PIANO_STYLE_6, R.drawable.img_piano_style6, false))
        list.add(NTDPianoStyle(Constants.PIANO_STYLE_7, R.drawable.img_piano_style7, false))
        return list
    }

    private fun setItemSelected(index: Int) {
        binding.icDotDf.setImageResource(R.drawable.ic_dot_vp)
        binding.icDot1.setImageResource(R.drawable.ic_dot_vp)
        binding.icDot2.setImageResource(R.drawable.ic_dot_vp)
        binding.icDot3.setImageResource(R.drawable.ic_dot_vp)
        binding.icDot4.setImageResource(R.drawable.ic_dot_vp)
        binding.icDot5.setImageResource(R.drawable.ic_dot_vp)
        binding.icDot6.setImageResource(R.drawable.ic_dot_vp)
        binding.icDot7.setImageResource(R.drawable.ic_dot_vp)

        when (index) {
            Constants.PIANO_STYLE_DEFAULT -> {
                binding.icDotDf.setImageResource(R.drawable.ic_dot_vp_selected)
            }
            Constants.PIANO_STYLE_1 -> {
                binding.icDot1.setImageResource(R.drawable.ic_dot_vp_selected)
            }
            Constants.PIANO_STYLE_2 -> {
                binding.icDot2.setImageResource(R.drawable.ic_dot_vp_selected)
            }
            Constants.PIANO_STYLE_3 -> {
                binding.icDot3.setImageResource(R.drawable.ic_dot_vp_selected)
            }
            Constants.PIANO_STYLE_4 -> {
                binding.icDot4.setImageResource(R.drawable.ic_dot_vp_selected)
            }
            Constants.PIANO_STYLE_5 -> {
                binding.icDot5.setImageResource(R.drawable.ic_dot_vp_selected)
            }
            Constants.PIANO_STYLE_6 -> {
                binding.icDot6.setImageResource(R.drawable.ic_dot_vp_selected)
            }
            Constants.PIANO_STYLE_7 -> {
                binding.icDot7.setImageResource(R.drawable.ic_dot_vp_selected)
            }
        }
    }

    private fun handleInterAds() {
        val timer = Timer()
        val adsTask: TimerTask = object : TimerTask() {
            override fun run() {
                enableShowInterAds = true
            }
        }

        if (Network().isNetworkAvailable(this@NTDViewPianoStyleActivity)) {
            if (fistItemClick || enableShowInterAds) {
                Admob.getInstance().showInterAds(
                    this@NTDViewPianoStyleActivity,
                    AdsConfig.getAdsConfig().mInterstitialAdStyle,
                    object : InterCallback() {
                        override fun onNextAction() {
                            super.onNextAction()
                            AdsConfig.getAdsConfig()
                                .loadAdsInterStyle(this@NTDViewPianoStyleActivity)

                            startActivity(Intent(this@NTDViewPianoStyleActivity, NTDViewPianoActivity::class.java))
                            finishAffinity()
                            NTDViewPianoActivity.openFromStyle = true

                            enableShowInterAds = false
                            fistItemClick = false
                            timer.schedule(adsTask, 15000)
                        }
                    })
            } else {
                startActivity(Intent(this@NTDViewPianoStyleActivity, NTDViewPianoActivity::class.java))
                finishAffinity()
                NTDViewPianoActivity.openFromStyle = true
            }
        } else {
            startActivity(Intent(this@NTDViewPianoStyleActivity, NTDViewPianoActivity::class.java))
            finishAffinity()
            NTDViewPianoActivity.openFromStyle = true
        }
    }

    companion object {
        var enableShowInterAds: Boolean = false
        var fistItemClick: Boolean = true
    }
}
package com.piano.keyboard.synthesia.learnpiano.play.music

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.nlbn.ads.callback.InterCallback
import com.nlbn.ads.util.Admob
import com.piano.keyboard.synthesia.learnpiano.play.music.adapter.NTDOtherViewPagerAdapter
import com.piano.keyboard.synthesia.learnpiano.play.music.ads_config.AdsConfig
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseActivityAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ActivityMainBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperSharePrefUtils
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.permission.NTDViewPermissionActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.permission.NTDHelperPermissionHelper
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.rate.NTDOtherRatingDialog
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.NTDOtherBottomMenu
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.Network
import java.util.Timer
import java.util.TimerTask

class NTDViewMainActivity : NTDOtherBaseActivityAppntd<ActivityMainBinding>() {
    private lateinit var pagerAdapter: NTDOtherViewPagerAdapter
    private var ratingDialog: NTDOtherRatingDialog? = null
    override fun setViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        setViewPager()
        loadBanner()

        //inter ads
        if (AdsConfig.getAdsConfig().mInterstitialAdAll == null) {
            AdsConfig.getAdsConfig().loadAdsInterAll(this)
        }
    }

    override fun initData() {

    }

    override fun initListener() {
        binding.bottomMenu.addListener(
            this@NTDViewMainActivity,
            object : NTDOtherBottomMenu.OnMenuClick {
                override fun onClick(action: NTDOtherBottomMenu.Action?) {
                    when (action) {
                        NTDOtherBottomMenu.Action.OPEN_HOME -> if (binding.vpMain.currentItem !== 0) {
                            binding.vpMain.currentItem = 0
                        }

                        NTDOtherBottomMenu.Action.OPEN_REPORT -> if (binding.vpMain.currentItem !== 1) {
                            binding.vpMain.currentItem = 1
                        }

                        NTDOtherBottomMenu.Action.OPEN_SETTING -> if (binding.vpMain.currentItem !== 2) {
                            binding.vpMain.currentItem = 2
                        }

                        else -> {}
                    }
                }
            })
    }

    private fun setViewPager() {
        pagerAdapter = NTDOtherViewPagerAdapter(this@NTDViewMainActivity)
        binding.vpMain.adapter = pagerAdapter
        binding.vpMain.isUserInputEnabled = false
    }

    private fun checkPermission(): Boolean {
        if (!NTDHelperPermissionHelper.checkPermissionRecordAudio(
                this@NTDViewMainActivity,
                ImageView(this@NTDViewMainActivity)
            )
        )
            return false
        return NTDHelperPermissionHelper.checkPermissionStorage(
            this@NTDViewMainActivity,
            ImageView(this@NTDViewMainActivity)
        )
    }

    private fun initRateDialog(isQuitApp: Boolean) {
        ratingDialog =
            NTDOtherRatingDialog(this)
        ratingDialog?.init(object : NTDOtherRatingDialog.OnPress {
            override fun send() {
                NTDHelperSharePrefUtils.putBoolean(
                    NTDHelperSharePrefUtils.IS_RATED, true
                )
                if (isQuitApp) {
                    finish()
                }

            }

            override fun rating() {
                NTDHelperSharePrefUtils.putBoolean(
                    NTDHelperSharePrefUtils.IS_RATED, true
                )
                if (isQuitApp) {
                    finish()
                }
            }

            override fun later() {
                ratingDialog?.dismiss()
                if (isQuitApp) {
                    finish()
                }
            }

        })

        ratingDialog?.show()
    }

    private fun handleInterAds() {
        val timer = Timer()
        val adsTask: TimerTask = object : TimerTask() {
            override fun run() {
                enableShowInterAds = true
            }
        }

        if (Network().isNetworkAvailable(this@NTDViewMainActivity)) {
            if (fistItemClick || enableShowInterAds) {
                Admob.getInstance().showInterAds(
                    this@NTDViewMainActivity,
                    AdsConfig.getAdsConfig().mInterstitialAdAll,
                    object : InterCallback() {
                        override fun onNextAction() {
                            super.onNextAction()
                            AdsConfig.getAdsConfig()
                                .loadAdsInterAll(this@NTDViewMainActivity)
                            enableShowInterAds = false
                            fistItemClick = false
                            timer.schedule(adsTask, 15000)
                        }
                    })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!checkPermission())
            startActivity(Intent(this@NTDViewMainActivity, NTDViewPermissionActivity::class.java))

        if (showRateFromInstrument) {
            initRateDialog(false)
            showRateFromInstrument = false
        }

        if (showInterAds) {
            handleInterAds()
            showInterAds = false
        }
    }

    private fun loadBanner() {
        if (Network().isNetworkAvailable(this)) {
            binding.rlBanner.visibility = View.VISIBLE
            Admob.getInstance()
                .loadBanner(this, getString(R.string.banner_all))
        } else {
            binding.rlBanner.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        val count = NTDHelperSharePrefUtils.getInt(NTDHelperSharePrefUtils.IS_BACK, 1)
        if (!NTDHelperSharePrefUtils.getBoolean(NTDHelperSharePrefUtils.IS_RATED, false)) {
            if (count == 1 || count % 2 != 0) {
                initRateDialog(true)
            } else {
                NTDHelperSharePrefUtils.setInt(NTDHelperSharePrefUtils.IS_BACK, count + 1)
                finish()
            }
        } else finish()
    }

    companion object {
        var showRateFromInstrument: Boolean = false
        var showInterAds: Boolean = false
        var enableShowInterAds: Boolean = false
        var fistItemClick: Boolean = true
    }
}
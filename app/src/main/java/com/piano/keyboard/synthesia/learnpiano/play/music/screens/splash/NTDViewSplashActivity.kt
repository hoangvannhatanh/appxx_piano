package com.piano.keyboard.synthesia.learnpiano.play.music.screens.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.nlbn.ads.callback.InterCallback
import com.nlbn.ads.util.Admob
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseActivityAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperSharePrefUtils
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ActivitySplashBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.intro.NTDViewIntroActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.language.NTDViewLanguageActivity

@SuppressLint("CustomSplashScreen")
class NTDViewSplashActivity : NTDOtherBaseActivityAppntd<ActivitySplashBinding>() {
    private var isFirstOpen: Boolean = false
    private var interCallback: InterCallback? = null

    override fun setViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        isFirstOpen = NTDHelperSharePrefUtils.getBoolean(
            NTDHelperSharePrefUtils.SHARE_PREF_OPEN_FIRST_APP, true)
        init()
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    private fun init() {
        setStatusBarGradiant()
        //ads
        interCallback = object : InterCallback() {
            override fun onNextAction() {
                super.onNextAction()
                openNextScreen()
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            Admob.getInstance()
                .loadSplashInterAds2(this, getString(R.string.inter_splash), 3000, interCallback)
        }, 2000)
    }

    override fun onResume() {
        super.onResume()
        Admob.getInstance().onCheckShowSplashWhenFail(this, interCallback, 1000)
    }

    override fun onStop() {
        super.onStop()
        Admob.getInstance().dismissLoadingDialog()
    }


    private fun openNextScreen() {
        if (isFirstOpen) {
            startActivity(Intent(this@NTDViewSplashActivity, NTDViewLanguageActivity::class.java))
        } else {
            startActivity(Intent(this@NTDViewSplashActivity, NTDViewIntroActivity::class.java))
        }
        finish()
    }

    private fun setStatusBarGradiant() {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
    }
}
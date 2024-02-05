package com.ntd.appid1.appid2.appid3.screens.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.databinding.ActivitySplashBinding
import com.ntd.appid1.appid2.appid3.screens.intro.NTDViewIntroActivity
import com.ntd.appid1.appid2.appid3.screens.language.NTDViewLanguageActivity

@SuppressLint("CustomSplashScreen")
class NTDViewSplashActivity : NTDOtherBaseActivityAppntd<ActivitySplashBinding>() {
    private var isFirstOpen: Boolean = false

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

        Handler(Looper.getMainLooper()).postDelayed({
            openNextScreen()
        }, 2000)
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
package com.ntd.appid1.appid2.appid3.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewbinding.ViewBinding
import com.ntd.appid1.appid2.appid3.local.NTDHelperPreferencesHelper
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.utils.NTDOtherUtils.init
import com.ntd.appid1.appid2.appid3.utils.NTDOtherUtils.setLocale

abstract class NTDOtherBaseActivityAppntd<T : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()
        NTDHelperSharePrefUtils.init(this)
        NTDHelperPreferencesHelper.init(this)
        init(this)
        setLocale(this, NTDHelperPreferencesHelper.getLanguage())
        binding = setViewBinding()
        setContentView(binding.root)

        //init
        initView()
        initData()
        initListener()
    }

    open fun initWindow() {
        window.apply {
            val background: Drawable = ColorDrawable(Color.parseColor("#FFFFFF"))
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = resources.getColor(android.R.color.black)
            setBackgroundDrawable(background)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    override fun onResume() {
        super.onResume()

        val windowInsetsControllerOne: WindowInsetsControllerCompat? =
            if (Build.VERSION.SDK_INT >= 30) {
                ViewCompat.getWindowInsetsController(window.decorView)
            } else {
                WindowInsetsControllerCompat(window, binding.root)
            }
        if (windowInsetsControllerOne == null) {
            return
        }
        windowInsetsControllerOne.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsControllerOne.hide(WindowInsetsCompat.Type.navigationBars())
        windowInsetsControllerOne.hide(WindowInsetsCompat.Type.systemGestures())
        window.decorView.setOnSystemUiVisibilityChangeListener { i: Int ->
            if (i == 0) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val windowInsetsControllerTwo: WindowInsetsControllerCompat? =
                        if (Build.VERSION.SDK_INT >= 30) {
                            ViewCompat.getWindowInsetsController(window.decorView)
                        } else {
                            WindowInsetsControllerCompat(window, binding.root)
                        }
                    if (windowInsetsControllerTwo != null) {
                        windowInsetsControllerTwo.hide(WindowInsetsCompat.Type.navigationBars())
                        windowInsetsControllerTwo.hide(WindowInsetsCompat.Type.systemGestures())
                    }
                }, 1500)
            }
        }
    }

    protected abstract fun setViewBinding(): T
    protected abstract fun initView()
    protected abstract fun initData()
    protected abstract fun initListener()
}
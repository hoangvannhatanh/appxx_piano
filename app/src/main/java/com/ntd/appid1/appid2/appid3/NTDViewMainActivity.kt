package com.ntd.appid1.appid2.appid3

import android.content.Intent
import android.view.LayoutInflater
import android.widget.ImageView
import com.ntd.appid1.appid2.appid3.adapter.NTDOtherViewPagerAdapter
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.databinding.ActivityMainBinding
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.screens.permission.NTDViewPermissionActivity
import com.ntd.appid1.appid2.appid3.screens.permission.NTDHelperPermissionHelper
import com.ntd.appid1.appid2.appid3.screens.rate.NTDOtherRatingDialog
import com.ntd.appid1.appid2.appid3.utils.NTDOtherBottomMenu

class NTDViewMainActivity : NTDOtherBaseActivityAppntd<ActivityMainBinding>() {
    private lateinit var pagerAdapter: NTDOtherViewPagerAdapter
    private var ratingDialog: NTDOtherRatingDialog? = null
    override fun setViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        setViewPager()
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

    override fun onResume() {
        super.onResume()
        if (!checkPermission())
            startActivity(Intent(this@NTDViewMainActivity, NTDViewPermissionActivity::class.java))

        if (showRateFromInstrument) {
            initRateDialog(false)
            showRateFromInstrument = false
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
    }
}
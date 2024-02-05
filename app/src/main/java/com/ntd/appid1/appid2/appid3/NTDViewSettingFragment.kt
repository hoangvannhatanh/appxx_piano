package com.ntd.appid1.appid2.appid3

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseFragmentAppntd
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.databinding.FragmentSettingBinding
import com.ntd.appid1.appid2.appid3.extensions.onAvoidDoubleClick
import com.ntd.appid1.appid2.appid3.screens.language.NTDViewLanguageActivity
import com.ntd.appid1.appid2.appid3.screens.policy.NTDViewPolicyActivity
import com.ntd.appid1.appid2.appid3.screens.rate.NTDOtherRatingDialog

class NTDViewSettingFragment : NTDOtherBaseFragmentAppntd<FragmentSettingBinding>() {
    private var ratingDialog: NTDOtherRatingDialog? = null
    private var lastClickTime: Long = 0
    override fun setViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, viewGroup, false)
    }

    override fun initView() {
        binding.clLanguage.onAvoidDoubleClick {
            startActivity(Intent(requireActivity(),NTDViewLanguageActivity::class.java))
        }

        binding.clPolicy.onAvoidDoubleClick {
            startActivity(Intent(requireActivity(), NTDViewPolicyActivity::class.java))
        }

        binding.clRate.setOnClickListener { initRateDialog() }

        binding.clShare.setOnClickListener { shareApp() }

        if (NTDHelperSharePrefUtils.getBoolean(
                NTDHelperSharePrefUtils.IS_RATED, false)) {
            hideRateItem()
        }
    }

    override fun viewListener() {}

    fun initData() {
    }

    private fun initRateDialog() {
        ratingDialog =
            NTDOtherRatingDialog(
                requireActivity()
            )
        ratingDialog?.init(object : NTDOtherRatingDialog.OnPress {
            override fun send() {
                NTDHelperSharePrefUtils.putBoolean(
                    NTDHelperSharePrefUtils.IS_RATED, true)
                hideRateItem()
            }

            override fun rating() {
                NTDHelperSharePrefUtils.putBoolean(
                    NTDHelperSharePrefUtils.IS_RATED, true)
                hideRateItem()
            }

            override fun later() {
                ratingDialog?.dismiss()
            }

        })

        ratingDialog?.show()
    }

    private fun hideRateItem() {
        binding.clRate.visibility = View.GONE
        val params: ViewGroup.MarginLayoutParams = binding.clRate.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(0, 0, 0, 0)
        binding.clRate.requestLayout()
    }

    private fun shareApp() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        val shareMessage =
            "${getString(R.string.app_name)} \n https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)))
    }
}
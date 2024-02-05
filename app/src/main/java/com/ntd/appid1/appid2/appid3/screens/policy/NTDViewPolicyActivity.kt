package com.ntd.appid1.appid2.appid3.screens.policy

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import com.ntd.appid1.appid2.appid3.BuildConfig
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.databinding.ActivityPolicyBinding

class NTDViewPolicyActivity : NTDOtherBaseActivityAppntd<ActivityPolicyBinding>() {
    override fun setViewBinding(): ActivityPolicyBinding {
        return ActivityPolicyBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        binding.btnBack.setOnClickListener { onBackPressed() }

        binding.tvPrivacyPolicy.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://studio-1998.web.app/policy.html")
                )
            )
        }

        binding.tvVersion.text = getString(R.string.version) + BuildConfig.VERSION_NAME
    }

    override fun initData() {}

    override fun initListener() {}
}
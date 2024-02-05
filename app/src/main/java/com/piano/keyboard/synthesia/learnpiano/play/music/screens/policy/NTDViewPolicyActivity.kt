package com.piano.keyboard.synthesia.learnpiano.play.music.screens.policy

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import com.nlbn.ads.util.AppOpenManager
import com.piano.keyboard.synthesia.learnpiano.play.music.BuildConfig
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseActivityAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ActivityPolicyBinding

class NTDViewPolicyActivity : NTDOtherBaseActivityAppntd<ActivityPolicyBinding>() {
    override fun setViewBinding(): ActivityPolicyBinding {
        return ActivityPolicyBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        binding.btnBack.setOnClickListener { onBackPressed() }

        binding.tvPrivacyPolicy.setOnClickListener {
            AppOpenManager.getInstance().disableAppResumeWithActivity(NTDViewPolicyActivity::class.java)
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

    override fun onResume() {
        super.onResume()
        AppOpenManager.getInstance().enableAppResumeWithActivity(NTDViewPolicyActivity::class.java)
    }
}
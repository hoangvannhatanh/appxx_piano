package com.ntd.appid1.appid2.appid3.screens.intro

import android.content.Intent
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.ntd.appid1.appid2.appid3.NTDViewMainActivity
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.adapter.NTDOtherIntroAdapter
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.databinding.ActivityIntroBinding
import com.ntd.appid1.appid2.appid3.extensions.onAvoidDoubleClick
import com.ntd.appid1.appid2.appid3.model.NTDModelIntro
import com.ntd.appid1.appid2.appid3.screens.permission.NTDViewPermissionActivity
import com.ntd.appid1.appid2.appid3.utils.Network

class NTDViewIntroActivity : NTDOtherBaseActivityAppntd<ActivityIntroBinding>() {

    private lateinit var introAdapter: NTDOtherIntroAdapter
    private var isOpenFirst: Boolean = false
    override fun setViewBinding(): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        isOpenFirst = NTDHelperSharePrefUtils.getBoolean(
            NTDHelperSharePrefUtils.SHARE_PREF_OPEN_FIRST_APP,true)
        init()
        onClick()
    }

    override fun initData() {}

    override fun initListener() {}

    fun init() {
        introAdapter = NTDOtherIntroAdapter(this@NTDViewIntroActivity)
        introAdapter.setData(mutableListOf<NTDModelIntro>().apply {
            add(
                NTDModelIntro(getString(R.string.title_intro_1),
                    getString(R.string.content_intro_1),
                    R.drawable.img_intro1)
            )
            add(
                NTDModelIntro(getString(R.string.title_intro_2),
                    getString(R.string.content_intro_2),
                    R.drawable.img_intro2)
            )
            add(
                NTDModelIntro(getString(R.string.title_intro_3),
                    getString(R.string.content_intro_3),
                    R.drawable.img_intro3)
            )
        })
        binding.layoutContainer.adapter = introAdapter
        binding.layoutContainer.currentItem = 0
        binding.layoutContainer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateUIByPageChanged(position)
            }
        })

    }

    fun updateUIByPageChanged(position: Int) {
        when (position) {
            0 -> {
                binding.tvTitle.text = getString(R.string.title_intro_1)
                binding.tvContent.text = getString(R.string.content_intro_1)
                binding.ivDot.setImageResource(R.drawable.bg_dot_1)
                binding.textNext.text = getString(R.string.next)
            }
            1 -> {
                binding.tvTitle.text = getString(R.string.title_intro_2)
                binding.tvContent.text = getString(R.string.content_intro_2)
                binding.ivDot.setImageResource(R.drawable.bg_dot_2)
                binding.textNext.text = getString(R.string.next)
            }
            2 -> {
                binding.tvTitle.text = getString(R.string.title_intro_3)
                binding.tvContent.text = getString(R.string.content_intro_3)
                binding.ivDot.setImageResource(R.drawable.bg_dot_3)
                binding.textNext.text = getString(R.string.start)
            }
        }
    }

    fun onClick() {
        binding.textNext.onAvoidDoubleClick {
            var index: Int = binding.layoutContainer.currentItem
            updateUIByPageChanged(index)
            if (index < 2) {
                ++index
                binding.layoutContainer.currentItem = index
            } else {
                if (isOpenFirst) {
                    startActivity(Intent(this, NTDViewPermissionActivity::class.java))
                } else {
                    NTDHelperSharePrefUtils.putBoolean(
                        NTDHelperSharePrefUtils.SHARE_PREF_OPEN_FIRST_APP, false)
                    startActivity(Intent(this, NTDViewMainActivity::class.java))
                }
                finish()
            }
            binding.textNext.text = if (index == 2) {
                resources.getString(R.string.start)
            } else {
                resources.getString(R.string.next)
            }
        }
    }
}
package com.ntd.appid1.appid2.appid3.screens.howto

import android.content.Intent
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.ntd.appid1.appid2.appid3.NTDViewMainActivity
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.adapter.NTDOtherHowtoAdapter
import com.ntd.appid1.appid2.appid3.adapter.NTDOtherIntroAdapter
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.databinding.ActivityHowtoBinding
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.databinding.ActivityIntroBinding
import com.ntd.appid1.appid2.appid3.extensions.onAvoidDoubleClick
import com.ntd.appid1.appid2.appid3.model.NTDModelIntro
import com.ntd.appid1.appid2.appid3.screens.permission.NTDViewPermissionActivity
import com.ntd.appid1.appid2.appid3.utils.Network

class NTDViewHowtoActivity : NTDOtherBaseActivityAppntd<ActivityHowtoBinding>() {

    private lateinit var introAdapter: NTDOtherHowtoAdapter
    private var isOpenFirst: Boolean = false
    override fun setViewBinding(): ActivityHowtoBinding {
        return ActivityHowtoBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        init()
        onClick()
    }

    override fun initData() {}

    override fun initListener() {}

    fun init() {
        introAdapter = NTDOtherHowtoAdapter(this@NTDViewHowtoActivity)
        introAdapter.setData(mutableListOf<NTDModelIntro>().apply {
            add(
                NTDModelIntro(getString(R.string.title_intro_1),
                    getString(R.string.content_intro_1),
                    R.drawable.howto1)
            )
            add(
                NTDModelIntro(getString(R.string.title_intro_2),
                    getString(R.string.content_intro_2),
                    R.drawable.howto2)
            )
            add(
                NTDModelIntro(getString(R.string.title_intro_3),
                    getString(R.string.content_intro_3),
                    R.drawable.howto3)
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
        binding.guitar.setOnClickListener {
            updateUIByPageChanged(0)
        }
        binding.drum.setOnClickListener {
            updateUIByPageChanged(1)
        }
        binding.saxaphone.setOnClickListener {
            updateUIByPageChanged(2)
        }
        binding.backpress.setOnClickListener{
            onBackPressed()
        }
    }

    fun updateUIByPageChanged(position: Int) {
        when (position) {
            0 -> {
                binding.tvContent.text =
                    getString(R.string.press_the_guitar_string_to_start_you_can_choose_chords_when_you_play_guitar)
                binding.ivDot.setImageResource(R.drawable.bg_dot_1)
                binding.textNext.text = getString(R.string.next)
                binding.guitar.isSelected = true
                binding.drum.isSelected = false
                binding.saxaphone.isSelected = false
            }
            1 -> {
                binding.tvContent.text =
                    getString(R.string.tap_drum_or_drum_plates_to_start_you_can_record_videos_and_change_themes)
                binding.ivDot.setImageResource(R.drawable.bg_dot_2)
                binding.textNext.text = getString(R.string.next)
                binding.guitar.isSelected = false
                binding.drum.isSelected = true
                binding.saxaphone.isSelected = false
            }
            2 -> {
                binding.tvContent.text =
                    getString(R.string.press_the_button_on_saxophone_to_start_you_can_record_videos_and_change_themes)
                binding.ivDot.setImageResource(R.drawable.bg_dot_3)
                binding.textNext.text = getString(R.string.start)
                binding.guitar.isSelected = false
                binding.drum.isSelected = false
                binding.saxaphone.isSelected = true
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
                binding.layoutContainer.currentItem = 0
            }
            binding.textNext.text = resources.getString(R.string.next)
        }
    }
}
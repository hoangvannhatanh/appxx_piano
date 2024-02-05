package com.ntd.appid1.appid2.appid3.screens.drum

import android.view.LayoutInflater
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.databinding.ActivityStyleDrumBinding
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import java.util.LinkedList
import kotlin.math.abs

class NTDViewStyleDrumActivity : NTDOtherBaseActivityAppntd<ActivityStyleDrumBinding>() {
    private var listStyle: LinkedList<Int> = LinkedList()
    private var imageSlideAdapter: NTDOtherImageSlideAdapter? = null
    private var compositePageTransformer: CompositePageTransformer? = null

    override fun setViewBinding(): ActivityStyleDrumBinding {
        return ActivityStyleDrumBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        listStyle.add(R.drawable.style1_drum)
        listStyle.add(R.drawable.style2_drum)

        binding.vp2.offscreenPageLimit = 3
        binding.vp2.clipToPadding = false
        binding.vp2.clipChildren = false

        compositePageTransformer = CompositePageTransformer()
        compositePageTransformer?.addTransformer(MarginPageTransformer(40))
        compositePageTransformer?.addTransformer(ViewPager2.PageTransformer { page, position ->
            page.scaleY= .85f + (1 - abs(position))*.15f
        })

        binding.vp2.setPageTransformer(compositePageTransformer)

        imageSlideAdapter = NTDOtherImageSlideAdapter(listStyle, this)
        binding.vp2.adapter = imageSlideAdapter
    }

    override fun initData() {
    }

    override fun initListener() {
        binding.icCheck.setOnClickListener {
            NTDHelperSharePrefUtils.setStyleDrum(binding.vp2.currentItem)
            finish()
        }
        binding.icBack.setOnClickListener {
            finish()
        }
        binding.vp2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> {
                        binding.icDot1.isSelected = true
                        binding.icDot2.isSelected = false
                    }
                    1 -> {
                        binding.icDot1.isSelected = false
                        binding.icDot2.isSelected = true
                    }
                }
            }
        })
    }
}
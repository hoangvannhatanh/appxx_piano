package com.ntd.appid1.appid2.appid3.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ntd.appid1.appid2.appid3.NTDViewHomeFragment
import com.ntd.appid1.appid2.appid3.NTDViewRecordFragment
import com.ntd.appid1.appid2.appid3.NTDViewSettingFragment

class NTDOtherViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return NTDViewHomeFragment()
            1 -> return NTDViewRecordFragment()
            2 -> return NTDViewSettingFragment()
        }
        //notifyDataSetChanged()
        return NTDViewHomeFragment()
    }
}
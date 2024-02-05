package com.piano.keyboard.synthesia.learnpiano.play.music.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.piano.keyboard.synthesia.learnpiano.play.music.NTDViewHomeFragment
import com.piano.keyboard.synthesia.learnpiano.play.music.NTDViewRecordFragment
import com.piano.keyboard.synthesia.learnpiano.play.music.NTDViewSettingFragment

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
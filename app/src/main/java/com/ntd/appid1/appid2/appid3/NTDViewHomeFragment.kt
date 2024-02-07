package com.ntd.appid1.appid2.appid3

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseFragmentAppntd
import com.ntd.appid1.appid2.appid3.databinding.FragmentHomeBinding
import com.ntd.appid1.appid2.appid3.extensions.onAvoidDoubleClick
import com.ntd.appid1.appid2.appid3.screens.drum.NTDViewDrumeActivity
import com.ntd.appid1.appid2.appid3.screens.guitar.NTDViewGuitarActivity
import com.ntd.appid1.appid2.appid3.screens.howto.NTDViewHowtoActivity
import com.ntd.appid1.appid2.appid3.screens.piano.NTDViewPianoActivity
import com.ntd.appid1.appid2.appid3.screens.saxophone.NTDViewSaxophoneActivity

class NTDViewHomeFragment : NTDOtherBaseFragmentAppntd<FragmentHomeBinding>() {
    override fun initView() {

    }

    override fun setViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, viewGroup, false)
    }

    override fun viewListener() {
        binding.tvHowto.setOnClickListener {
            startActivity(Intent(requireActivity(), NTDViewHowtoActivity::class.java))
        }
        binding.layoutPiano.onAvoidDoubleClick {
            startActivity(Intent(requireActivity(), NTDViewPianoActivity::class.java))
        }

        binding.layoutGuitar.onAvoidDoubleClick {
            startActivity(Intent(requireActivity(), NTDViewGuitarActivity::class.java))
        }

        binding.layoutDrumset.onAvoidDoubleClick {
            startActivity(Intent(requireActivity(), NTDViewDrumeActivity::class.java))
        }

        binding.layoutSaxophone.onAvoidDoubleClick {
            startActivity(Intent(requireActivity(), NTDViewSaxophoneActivity::class.java))
        }
    }
}
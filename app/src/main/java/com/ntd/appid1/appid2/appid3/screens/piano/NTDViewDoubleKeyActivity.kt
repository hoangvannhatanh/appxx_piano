package com.ntd.appid1.appid2.appid3.screens.piano

import android.view.LayoutInflater
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.databinding.ActivityDoubleKeyBinding
import com.ntd.appid1.appid2.appid3.piano_lib.entity.NTDOtherPiano
import com.ntd.appid1.appid2.appid3.piano_lib.listener.NTDOtherOnPianoListener

class NTDViewDoubleKeyActivity : NTDOtherBaseActivityAppntd<ActivityDoubleKeyBinding>(),
    NTDOtherOnPianoListener {
    override fun setViewBinding(): ActivityDoubleKeyBinding {
        return ActivityDoubleKeyBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        binding.pv.setSoundPollMaxStream(10)
        binding.pv.setCorD0(true)
        binding.pv2.setSoundPollMaxStream(10)
        binding.pv2.setCorD0(true)
        onClick()
    }

    override fun initData() {
    }

    override fun initListener() {
        binding.sb.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.pv.scroll(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        binding.sb2.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.pv2.scroll(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
    }

    private fun onClick() {
        binding.pv.setPianoListener(this)
        binding.pv2.setPianoListener(this)
    }

    override fun onPianoInitFinish() {}

    override fun onPianoClick(
        type: NTDOtherPiano.PianoKeyType?,
        voice: NTDOtherPiano.PianoVoice?,
        group: Int,
        positionOfGroup: Int
    ) {}
}
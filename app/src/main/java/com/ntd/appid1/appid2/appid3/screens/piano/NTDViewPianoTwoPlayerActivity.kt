package com.ntd.appid1.appid2.appid3.screens.piano

import android.view.LayoutInflater
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.databinding.ActivityPianoTwoPlayerBinding

class NTDViewPianoTwoPlayerActivity : NTDOtherBaseActivityAppntd<ActivityPianoTwoPlayerBinding>() {
    override fun setViewBinding(): ActivityPianoTwoPlayerBinding {
        return ActivityPianoTwoPlayerBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {
        binding.sb1.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.pianoPlayer1.scroll(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.sb2.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.pianoPlayer2.scroll(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }
}
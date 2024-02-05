package com.ntd.appid1.appid2.appid3.screens.piano.dialog

import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.view.LayoutInflater
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.databinding.DialogPianoSettingBinding

class NTDOtherPianoSettingDialog(ctx: Context, private val onSettingSaved: () -> Unit) :
    NTDOtherBaseDialogAppntd<DialogPianoSettingBinding>(ctx) {

    private lateinit var audioManager: AudioManager
    private var receiver = MediaButtonReceiver()
    private var ctx: Context? = null
    override fun setViewBinding(context: Context?): DialogPianoSettingBinding {
        return DialogPianoSettingBinding.inflate(LayoutInflater.from(context))
    }

    override fun initView(context: Context?) {
        ctx = context
        context?.registerReceiver(receiver, IntentFilter("android.media.VOLUME_CHANGED_ACTION"))
        audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        binding.sbTempo.progress = NTDHelperSharePrefUtils.getInt(
            NTDHelperSharePrefUtils.PIANO_TEMPO, 15)
        binding.switchShowNote.isActivated =
            NTDHelperSharePrefUtils.getBoolean(
                NTDHelperSharePrefUtils.PIANO_SHOW_NOTE, false)

        binding.sbVolume.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        binding.sbVolume.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        binding.tvMaxTempo.text = binding.sbTempo.progress.toString()

        binding.tvClose.setOnClickListener { dismiss() }

        binding.tvSave.setOnClickListener {
            NTDHelperSharePrefUtils.putInt(
                NTDHelperSharePrefUtils.PIANO_TEMPO, binding.sbTempo.progress)
            NTDHelperSharePrefUtils.putBoolean(
                NTDHelperSharePrefUtils.PIANO_SHOW_NOTE,
                binding.switchShowNote.isActivated
            )
            onSettingSaved.invoke()
            dismiss()
        }

        binding.sbVolume.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.switchShowNote.setOnClickListener {
            binding.switchShowNote.isActivated = !binding.switchShowNote.isActivated
        }

        binding.sbTempo.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvMaxTempo.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        ctx?.unregisterReceiver(receiver)
        super.setOnDismissListener(listener)
    }

    inner class MediaButtonReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (isShowing) {
                binding.sbVolume.progress =
                    (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC))
            }
        }
    }
}
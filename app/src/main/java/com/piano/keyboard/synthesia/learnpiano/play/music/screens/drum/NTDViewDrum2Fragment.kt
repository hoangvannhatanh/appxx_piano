package com.piano.keyboard.synthesia.learnpiano.play.music.screens.drum

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.SoundPool
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseFragmentAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.FragmentDrum2Binding

class NTDViewDrum2Fragment : NTDOtherBaseFragmentAppntd<FragmentDrum2Binding>() {
    private var soundPool: SoundPool? = null
    private var soundCrashCenter: Int? = null
    private var soundKick: Int? = null
    private var soundSnare: Int? = null
    private var soundDrumSmall: Int? = null
    private var soundRealistic: Int? = null
    private var soundCrash: Int? = null
    private var soundTom: Int? = null
    private var soundFloor: Int? = null
    override fun setViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentDrum2Binding {
        return FragmentDrum2Binding.inflate(inflater, viewGroup, false)
    }

    override fun initView() {
        val audioAttributes: AudioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool =
            SoundPool.Builder().setMaxStreams(30).setAudioAttributes(audioAttributes).build()
        soundCrashCenter = soundPool?.load(requireContext(), R.raw.drum_center, 1)
        soundKick = soundPool?.load(requireContext(), R.raw.kick, 1)
        soundSnare = soundPool?.load(requireContext(), R.raw.snare, 1)
        soundDrumSmall = soundPool?.load(requireContext(), R.raw.ride, 1)
        soundRealistic = soundPool?.load(requireContext(), R.raw.drum_small, 1)
        soundCrash = soundPool?.load(requireContext(), R.raw.crash, 1)
        soundTom = soundPool?.load(requireContext(), R.raw.tom, 1)
        soundFloor = soundPool?.load(requireContext(), R.raw.tom_floor, 1)
    }

    override fun viewListener() {
        initSoundDrum()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initSoundDrum() {
        binding.imgKickBottomLeft.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundKick!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgKickBottomLeft)
                    true
                }

                else -> false
            }
        }
        binding.imgKickBottomRight.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundKick!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgKickBottomRight)
                    true
                }

                else -> false
            }
        }
        binding.imgKickBottomCenter.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundSnare!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgKickBottomCenter)
                    true
                }

                else -> false
            }
        }
        binding.imgKickTopLeft.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundFloor!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgKickTopLeft)
                    true
                }

                else -> false
            }
        }
        binding.imgKickTopRight.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundFloor!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgKickTopRight)
                    true
                }

                else -> false
            }
        }
        binding.imgKickTopCenter.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundTom!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgKickTopCenter)
                    true
                }

                else -> false
            }
        }
        // no sound yet
        binding.imgKickSmallRight.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundTom!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgKickSmallRight)
                    true
                }

                else -> false
            }
        }
        // --
        binding.imgDrumBigLeft.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundCrash!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgDrumBigLeft)
                    true
                }

                else -> false
            }
        }
        binding.imgDrumBigRight.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundCrash!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgDrumBigRight)
                    true
                }

                else -> false
            }
        }
        binding.imgDrumBigCenter.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundCrashCenter!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgDrumBigCenter)
                    true
                }

                else -> false
            }
        }
        binding.imgDrumSmallLeft.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundDrumSmall!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgDrumSmallLeft)
                    true
                }

                else -> false
            }
        }
        binding.imgDrumSmallRight.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    soundPool?.play(soundDrumSmall!!, 1f, 1f, 0, 0, 1f)
                    NTDHelperDrumeHelper.animScaleDrum(binding.imgDrumSmallRight)
                    true
                }

                else -> false
            }
        }

    }

}
package com.piano.keyboard.synthesia.learnpiano.play.music.screens.saxophone

import android.annotation.SuppressLint
import android.app.Dialog
import android.media.MediaRecorder
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.nlbn.ads.callback.InterCallback
import com.nlbn.ads.util.Admob
import com.piano.keyboard.synthesia.learnpiano.play.music.NTDViewMainActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.ads_config.AdsConfig
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseActivityAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.database.NTDOtherRecord
import com.piano.keyboard.synthesia.learnpiano.play.music.database.NTDOtherRecordDB
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ActivitySaxophoneBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperSharePrefUtils
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.piano.dialog.NTDOtherRecordSuccessDialog
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.piano.dialog.NTDOtherSaveRecordDialog
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.rate.NTDOtherRatingDialog
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.Network
import java.io.File

import java.io.IOException
import java.util.Date
import java.util.Timer
import java.util.TimerTask


class NTDViewSaxophoneActivity : NTDOtherBaseActivityAppntd<ActivitySaxophoneBinding>(), TimerListener {
    private var timeTotal: Long = 0
    private var mediaRecorder: MediaRecorder? = null
    private var outputPath: String? = ""
    private var fileName: String? = ""
    private var isRecording: Boolean = false
    private lateinit var dialogSaveRecord: NTDOtherSaveRecordDialog
    private lateinit var dialogRecordSuccess: Dialog
    override fun setViewBinding(): ActivitySaxophoneBinding {
        return ActivitySaxophoneBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        init()
        onClick()
        handleTimer()

        loadBanner()
    }

    private fun handleTimer() {
        binding.icRecord.setOnClickListener {
            if (!TimerManager.isTimeCounting()) {
                mediaRecorder = MediaRecorder()
                TimerManager.startTimer(this)
                TimerManager.setTimeCounting(true)
                binding.icRecord.isSelected = true
                binding.ctTime.isSelected = true
                isRecording = true
                startRecording()
            } else {
                isRecording = false
                whenStopRecording()
            }
        }
    }

    private fun whenBackPress() {
        if (isRecording) {
            whenStopRecording()
        } else {
            mediaRecorder?.release()
            TimerManager.releaseTimer()
            onBackPressed()
        }

    }

    private fun whenStopRecording() {
        TimerManager.stopTimer()
        timeTotal = TimerManager.timeTotal()
        TimerManager.setTimeCounting(false)
        binding.icRecord.isSelected = false
        binding.ctTime.isSelected = false
        stopRecording()
        dialogSaveRecord.show()
        dialogSaveRecord.setCanceledOnTouchOutside(false)

    }

    private fun stopRecording() {
        mediaRecorder?.release()
    }


    private fun startRecording() {
        try {
            mediaRecorder = MediaRecorder()
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            outputPath = getOutputPath()
            mediaRecorder?.setOutputFile(outputPath)
            mediaRecorder?.prepare()
            mediaRecorder?.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getOutputPath(): String {
        var file: File
        val filesDir = filesDir
        val file2 = File(filesDir, "/" + System.currentTimeMillis() + ".aac")
        file2.also { file = it }
        if (!file2.exists()) {
            file.createNewFile()
        }
        return file.path
    }

    override fun onTimerTick(time: String) {
        runOnUiThread {
            binding.tvTime.text = time
        }
    }

    override fun onTimerFinish() {
        runOnUiThread {
            binding.tvTime.text = "00:00"
        }
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    private fun init() {
        NTDHelperSaxophoneHelper.getListMedia(this@NTDViewSaxophoneActivity)
        setStatusBarGradiant(R.drawable.bg_saxophone)
        dialogSaveRecord = NTDOtherSaveRecordDialog(this@NTDViewSaxophoneActivity) { file ->


            fileName = file
            NTDOtherRecordDB.getInstance(this@NTDViewSaxophoneActivity).recordDAO().insertRecord(
                NTDOtherRecord(
                    id = null,
                    fileName = fileName!!,
                    filePath = outputPath!!,
                    durationTime = TimerManager.timeTotal(),
                    createdAt = Date().time
                )
            )
            dialogRecordSuccess = NTDOtherRecordSuccessDialog(this)
            dialogRecordSuccess.show()
            dialogRecordSuccess.setOnDismissListener { if (isRecording) onBackPressed() }
        }

        dialogSaveRecord.binding.tvClose.setOnClickListener {
            dialogSaveRecord.dismiss()
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun onClick() {
        binding.icBack.setOnClickListener {
            whenBackPress()
            dialogSaveRecord.binding.tvClose.setOnClickListener {
                if (isRecording) {
                    NTDViewMainActivity.showInterAds = true
                    finish()
                }
                dialogSaveRecord.binding.edtNameRecord.text.clear()
            }
        }

        binding.button1.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_0)
                    changeImage(
                        binding.button1,
                        R.drawable.item_saxo_01,
                        R.drawable.item_saxo_01_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button2.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_1)
                    changeImage(
                        binding.button2,
                        R.drawable.item_saxo_02,
                        R.drawable.item_saxo_02_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button3.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_2)
                    changeImage(
                        binding.button3,
                        R.drawable.item_saxo_03,
                        R.drawable.item_saxo_03_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button4.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_3)
                    changeImage(
                        binding.button4,
                        R.drawable.item_saxo_04,
                        R.drawable.item_saxo_04_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button5.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_4)
                    changeImage(
                        binding.button5,
                        R.drawable.item_saxo_05,
                        R.drawable.item_saxo_05_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button6.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_5)
                    changeImage(
                        binding.button6,
                        R.drawable.item_saxo_06,
                        R.drawable.item_saxo_06_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button7.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_6)
                    changeImage(
                        binding.button7,
                        R.drawable.item_saxo_07,
                        R.drawable.item_saxo_07_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button8.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_7)
                    changeImage(
                        binding.button8,
                        R.drawable.item_saxo_08,
                        R.drawable.item_saxo_08_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button9.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_8)
                    changeImage(
                        binding.button9,
                        R.drawable.item_saxo_09,
                        R.drawable.item_saxo_09_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button10.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_9)
                    changeImage(
                        binding.button10,
                        R.drawable.item_saxo_10,
                        R.drawable.item_saxo_10_enable
                    )
                    true
                }

                else -> false
            }
        }
        binding.button11.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    playSound(NTDHelperSaxophoneHelper.INDEX_BUTTON_10)
                    changeImage(
                        binding.button11,
                        R.drawable.item_saxo_11,
                        R.drawable.item_saxo_11_enable
                    )
                    true
                }

                else -> false
            }
        }
    }

    private fun playSound(index: Int) {
        NTDHelperSaxophoneHelper.playSong(index, this@NTDViewSaxophoneActivity)
    }

    private fun changeImage(imageView: ImageView, imageInit: Int, imageChange: Int) {
        imageView.setImageResource(imageChange)
        imageView.postDelayed({
            imageView.setImageResource(imageInit)
        }, 50)
    }

    override fun onBackPressed() {
        var count = NTDHelperSharePrefUtils.getInt(NTDHelperSharePrefUtils.IS_BACK_FROM_INSTRUMENT,0)
        if (!NTDHelperSharePrefUtils.getBoolean(NTDHelperSharePrefUtils.IS_RATED, false)) {
            count++
            NTDHelperSharePrefUtils.setInt(NTDHelperSharePrefUtils.IS_BACK_FROM_INSTRUMENT, count)
            if (count % 2 == 0) {
                NTDViewMainActivity.showRateFromInstrument = true
                finish()
            } else {
                NTDViewMainActivity.showInterAds = true
                finish()
            }
        } else {
            NTDViewMainActivity.showInterAds = true
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NTDHelperSaxophoneHelper.turnOffSong()
    }

    override fun onPause() {
        super.onPause()
        NTDHelperSaxophoneHelper.turnOffSong()
    }

    private fun setStatusBarGradiant(currentTheme: Int) {
        val background = ContextCompat.getDrawable(this, currentTheme)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }

    private fun loadBanner() {
        if (Network().isNetworkAvailable(this)) {
            binding.rlBanner.visibility = View.VISIBLE
            Admob.getInstance()
                .loadBanner(this, getString(R.string.banner_all))
        } else {
            binding.rlBanner.visibility = View.GONE
        }
    }
}
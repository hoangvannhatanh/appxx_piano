package com.piano.keyboard.synthesia.learnpiano.play.music.screens.drum

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.nlbn.ads.callback.InterCallback
import com.nlbn.ads.util.Admob
import com.piano.keyboard.synthesia.learnpiano.play.music.NTDViewMainActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.ads_config.AdsConfig
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseActivityAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseDialogAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.database.NTDOtherRecordDB
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ActivityDrumeBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.DialogSaveRecordBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.local.NTDHelperSharePrefUtils
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.guitar.NTDViewGuitarActivity
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.piano.dialog.NTDOtherRecordSuccessDialog
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.piano.dialog.NTDOtherSaveRecordDialog
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.rate.NTDOtherRatingDialog
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.saxophone.TimerListener
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.saxophone.TimerManager
import com.piano.keyboard.synthesia.learnpiano.play.music.utils.Network
import java.io.File
import java.io.IOException
import java.util.Date
import java.util.Timer
import java.util.TimerTask

class NTDViewDrumeActivity : NTDOtherBaseActivityAppntd<ActivityDrumeBinding>(), TimerListener {
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var outputPath: String? = ""
    private var fileName: String? = null
    private lateinit var dialogSaveRecord: NTDOtherBaseDialogAppntd<DialogSaveRecordBinding>
    private lateinit var dialogRecordSuccess: Dialog
    private var isRecording: Boolean = false
    private val drum1Fragment: NTDViewDrum1Fragment = NTDViewDrum1Fragment()
    private val drum2Fragment: NTDViewDrum2Fragment = NTDViewDrum2Fragment()

    override fun setViewBinding(): ActivityDrumeBinding {
        return ActivityDrumeBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        init()
        onClick()

        loadBanner()
    }

    override fun initData() {}

    override fun initListener() {}

    private fun init() {
        mediaPlayer = MediaPlayer()
        dialogSaveRecord = NTDOtherSaveRecordDialog(this@NTDViewDrumeActivity) { file ->


            fileName = file
            NTDOtherRecordDB.getInstance(this@NTDViewDrumeActivity).recordDAO().insertRecord(
                com.piano.keyboard.synthesia.learnpiano.play.music.database.NTDOtherRecord(
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
        TimerManager.setTimeCounting(false)
        binding.icRecord.isSelected = false
        binding.ctTime.isSelected = false
        stopRecording()
        dialogSaveRecord.show()
        dialogSaveRecord.setCanceledOnTouchOutside(false)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onClick() {
        binding.btnChooseStyle.setOnClickListener { startActivity(Intent(this, NTDViewStyleDrumActivity::class.java)) }
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
        binding.icRecord.setOnClickListener {
            if (!TimerManager.isTimeCounting()) {
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

    private fun stopRecording() {
        mediaRecorder?.stop()
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
    private fun changeStyle(style: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.styleDrum, style)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
        when(NTDHelperSharePrefUtils.getStyleDrum()){
            0 -> changeStyle(drum1Fragment)
            1 -> changeStyle(drum2Fragment)
        }
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
        super.onBackPressed()
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
package com.ntd.appid1.appid2.appid3.screens.drum

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.ntd.appid1.appid2.appid3.NTDViewMainActivity
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd
import com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDB
import com.ntd.appid1.appid2.appid3.databinding.ActivityDrumeBinding
import com.ntd.appid1.appid2.appid3.databinding.DialogSaveRecordBinding
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherRecordSuccessDialog
import com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherSaveRecordDialog
import com.ntd.appid1.appid2.appid3.screens.saxophone.TimerListener
import com.ntd.appid1.appid2.appid3.screens.saxophone.TimerManager
import java.io.File
import java.io.IOException
import java.util.Date

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
    }

    override fun initData() {}

    override fun initListener() {}

    private fun init() {
        mediaPlayer = MediaPlayer()
        dialogSaveRecord = NTDOtherSaveRecordDialog(this@NTDViewDrumeActivity) { file ->


            fileName = file
            NTDOtherRecordDB.getInstance(this@NTDViewDrumeActivity).recordDAO().insertRecord(
                com.ntd.appid1.appid2.appid3.database.NTDOtherRecord(
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
}
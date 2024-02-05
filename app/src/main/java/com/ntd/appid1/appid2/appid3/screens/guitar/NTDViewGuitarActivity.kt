package com.ntd.appid1.appid2.appid3.screens.guitar

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaRecorder
import android.media.SoundPool
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.ntd.appid1.appid2.appid3.NTDViewMainActivity
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.adapter.NTDOtherGuitarAdapter
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd
import com.ntd.appid1.appid2.appid3.database.NTDOtherRecord
import com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDB
import com.ntd.appid1.appid2.appid3.databinding.ActivityGuitarBinding
import com.ntd.appid1.appid2.appid3.databinding.DialogRecordSavedBinding
import com.ntd.appid1.appid2.appid3.databinding.DialogSaveRecordBinding
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.model.NTDModelGuitar
import com.ntd.appid1.appid2.appid3.screens.permission.NTDViewPermissionActivity
import com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherRecordSuccessDialog
import com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherSaveRecordDialog
import com.ntd.appid1.appid2.appid3.screens.saxophone.TimerListener
import com.ntd.appid1.appid2.appid3.screens.saxophone.TimerManager
import java.io.File
import kotlin.jvm.internal.Intrinsics
import com.ntd.appid1.appid2.appid3.screens.piano.NTDOtherPianoHelper


class NTDViewGuitarActivity : NTDOtherBaseActivityAppntd<ActivityGuitarBinding>(), TimerListener {
    private var flag: Int = -1
    private lateinit var pianoAdapter: NTDOtherGuitarAdapter
    private lateinit var guitars: MutableList<NTDModelGuitar>
    private lateinit var images: MutableList<ImageView>
    private var timeTotal: Long? = 0
    private var soundPool: SoundPool? = null

    private var soundF1: Int? = null
    private var soundG1: Int? = null
    private var soundE11: Int? = null
    private var soundA12: Int? = null
    private var soundF2: Int? = null
    private var soundE2: Int? = null
    private var soundC2: Int? = null
    private var soundA3: Int? = null
    private var soundF3: Int? = null
    private var soundBm3: Int? = null
    private var soundD23: Int? = null
    private var soundEm3: Int? = null
    private var soundA4: Int? = null
    private var soundG24: Int? = null
    private var soundBm4: Int? = null
    private var soundE4: Int? = null
    private var soundDm4: Int? = null
    private var soundF4: Int? = null
    private var soundA5: Int? = null
    private var soundB5: Int? = null
    private var soundB25: Int? = null
    private var soundDm5: Int? = null
    private var soundF5: Int? = null
    private var soundE36: Int? = null
    private var soundBm6: Int? = null
    private var soundDm6: Int? = null
    private var soundF6: Int? = null
    private var soundG6: Int? = null
    private lateinit var db: NTDOtherRecordDB
    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var fileCurrent: File
    private lateinit var dialogSaveRecord: NTDOtherBaseDialogAppntd<DialogSaveRecordBinding>
    private lateinit var dialogRecordSuccess: NTDOtherBaseDialogAppntd<DialogRecordSavedBinding>
    private var isRecording: Boolean = false

    override fun setViewBinding(): ActivityGuitarBinding {
        return ActivityGuitarBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        init()
        onTouch()
        onClick()
    }

    override fun initData() {}

    override fun initListener() {}

    private fun init() {

        db = NTDOtherRecordDB.getInstance(this@NTDViewGuitarActivity)

        dialogRecordSuccess = NTDOtherRecordSuccessDialog(this)

        dialogSaveRecord = NTDOtherSaveRecordDialog(this@NTDViewGuitarActivity) { fileName ->

            if (isRecording) {
                val startTime = System.currentTimeMillis()

                val record = NTDOtherRecord(
                    id = null,
                    fileName = fileName,
                    filePath = fileCurrent.path,
                    durationTime = timeTotal!!,
                    createdAt = startTime
                )
                db.recordDAO().insertRecord(record)
                isRecording = false
            }
            dialogRecordSuccess.show()
        }

        initSoundPool()
        initNote()
        images = mutableListOf()
        guitars = mutableListOf()
        guitars = NTDHelperGuitarHelper.getGuitar()
        pianoAdapter =
            NTDOtherGuitarAdapter(
                this@NTDViewGuitarActivity,
                guitars,
                object : NTDOtherGuitarAdapter.ClickItem {
                    override fun chooseButton(item: NTDModelGuitar, index: Int) {
                        handleChooseButton(item, index)
                    }
                })
        binding.rcv.apply {
            layoutManager = GridLayoutManager(this@NTDViewGuitarActivity, 2)
            adapter = pianoAdapter
        }

        binding.lineGuitar1.isActivated = true
        binding.lineGuitar2.isActivated = true

        mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(this@NTDViewGuitarActivity)
        } else {
            MediaRecorder()
        }

        getListImage()
    }

    private fun initSoundPool() {
        val audioAttributes: AudioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool =
            SoundPool.Builder().setMaxStreams(10).setAudioAttributes(audioAttributes).build()
    }

    private fun initNote() {
        soundF1 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.f_1, 0)
        soundG1 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.g_1, 0)
        soundE11 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.e1_1, 0)

        soundA12 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.a1_2, 0)
        soundF2 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.f_2, 0)
        soundE2 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.e_2, 0)
        soundC2 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.c_2, 0)

        soundA3 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.am_3, 0)
        soundF3 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.f_3, 0)
        soundBm3 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.bm_3, 0)
        soundD23 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.d2_3, 0)
        soundEm3 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.em_3, 0)

        soundA4 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.am_4, 0)
        soundBm4 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.bm_4, 0)
        soundG24 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.g2_4, 0)
        soundE4 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.e_4, 0)
        soundDm4 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.dm_4, 0)
        soundF4 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.f_4, 0)

        soundA5 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.am_5, 0)
        soundB5 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.bm_5, 0)
        soundB25 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.b2_5, 0)
        soundDm5 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.dm_5, 0)
        soundF5 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.f_5, 0)

        soundE36 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.e3_6, 0)
        soundBm6 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.bm_6, 0)
        soundDm6 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.dm_6, 0)
        soundF6 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.f_6, 0)
        soundG6 = soundPool?.load(this@NTDViewGuitarActivity, R.raw.g_6, 0)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouch() {
        binding.lineGuitar1.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    NTDHelperGuitarHelper.loadAnimation(binding.lineGuitar1)
                    when (flag) {
                        NTDHelperGuitarHelper.flagAm -> {
                        }

                        NTDHelperGuitarHelper.flagB -> {
                        }

                        NTDHelperGuitarHelper.flagC -> {
                        }

                        NTDHelperGuitarHelper.flagDm -> {
                        }

                        NTDHelperGuitarHelper.flagF -> {
                            soundPool?.play(soundF1!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagG -> {
                            soundPool?.play(soundG1!!, 1f, 1f, 0, 0, 1f)
                        }

                        else -> {
                            soundPool?.play(soundE11!!, 1f, 1f, 0, 0, 1f)
                        }
                    }
                    true
                }

                else -> false
            }
        }

        binding.lineGuitar2.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    NTDHelperGuitarHelper.loadAnimation(binding.lineGuitar2)
                    when (flag) {
                        NTDHelperGuitarHelper.flagAm -> {
                            soundPool?.play(soundA12!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagC -> {
                            soundPool?.play(soundF2!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagB -> {
                            soundPool?.play(soundE2!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagE -> {
                            soundPool?.play(soundE2!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagDm -> {
                        }

                        NTDHelperGuitarHelper.flagF -> {
                            soundPool?.play(soundC2!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagEm -> {
                            soundPool?.play(soundE2!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagG -> {
                            soundPool?.play(soundE2!!, 1f, 1f, 0, 0, 1f)
                        }

                        else -> {
                            soundPool?.play(soundA12!!, 1f, 1f, 0, 0, 1f)
                        }
                    }
                    true
                }

                else -> false
            }
        }

        binding.lineGuitar3.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    NTDHelperGuitarHelper.loadAnimation(binding.lineGuitar3)
                    when (flag) {
                        NTDHelperGuitarHelper.flagAm -> {
                            soundPool?.play(soundA3!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagC -> {
                            soundPool?.play(soundF3!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagB -> {
                            soundPool?.play(soundBm3!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagE -> {
                            soundPool?.play(soundF3!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagDm -> {
                            soundPool?.play(soundD23!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagF -> {
                            soundPool?.play(soundF3!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagEm -> {
                            soundPool?.play(soundEm3!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagG -> {
                            soundPool?.play(soundD23!!, 1f, 1f, 0, 0, 1f)
                        }

                        else -> {
                            soundPool?.play(soundD23!!, 1f, 1f, 0, 0, 1f)
                        }
                    }
                    true
                }

                else -> false
            }
        }

        binding.lineGuitar4.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    NTDHelperGuitarHelper.loadAnimation(binding.lineGuitar4)
                    when (flag) {
                        NTDHelperGuitarHelper.flagAm -> {
                            soundPool?.play(soundA4!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagC -> {
                            soundPool?.play(soundG24!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagB -> {
                            soundPool?.play(soundBm4!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagE -> {
                            soundPool?.play(soundE4!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagDm -> {
                            soundPool?.play(soundDm4!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagF -> {
                            soundPool?.play(soundF4!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagEm -> {
                            soundPool?.play(soundG24!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagG -> {
                            soundPool?.play(soundG24!!, 1f, 1f, 0, 0, 1f)
                        }

                        else -> {
                            soundPool?.play(soundG24!!, 1f, 1f, 0, 0, 1f)
                        }
                    }
                    true
                }

                else -> false
            }
        }

        binding.lineGuitar5.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    NTDHelperGuitarHelper.loadAnimation(binding.lineGuitar5)
                    when (flag) {
                        NTDHelperGuitarHelper.flagAm -> {
                            soundPool?.play(soundA5!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagC -> {
                            soundPool?.play(soundA5!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagB -> {
                            soundPool?.play(soundB5!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagE -> {
                            soundPool?.play(soundB25!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagDm -> {
                            soundPool?.play(soundDm5!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagF -> {
                            soundPool?.play(soundF5!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagEm -> {
                            soundPool?.play(soundB25!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagG -> {
                            soundPool?.play(soundB25!!, 1f, 1f, 0, 0, 1f)
                        }

                        else -> {
                            soundPool?.play(soundB25!!, 1f, 1f, 0, 0, 1f)
                        }
                    }
                    true
                }

                else -> false
            }
        }

        binding.lineGuitar6.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    NTDHelperGuitarHelper.loadAnimation(binding.lineGuitar6)
                    when (flag) {
                        NTDHelperGuitarHelper.flagAm -> {
                            soundPool?.play(soundE36!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagC -> {
                            soundPool?.play(soundE36!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagB -> {
                            soundPool?.play(soundDm6!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagE -> {
                            soundPool?.play(soundE36!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagDm -> {
                            soundPool?.play(soundDm6!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagF -> {
                            soundPool?.play(soundF6!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagEm -> {
                            soundPool?.play(soundE36!!, 1f, 1f, 0, 0, 1f)
                        }

                        NTDHelperGuitarHelper.flagG -> {
                            soundPool?.play(soundG6!!, 1f, 1f, 0, 0, 1f)
                        }

                        else -> {
                            soundPool?.play(soundE36!!, 1f, 1f, 0, 0, 1f)
                        }
                    }
                    true
                }

                else -> false
            }
        }
    }

    private fun onClick() {
        binding.btnBack.setOnClickListener { onBackPressed() }

        binding.btnRecord.setOnClickListener {
            record()
        }

        dialogSaveRecord.binding.tvClose.setOnClickListener {
            isRecording = false
            dialogSaveRecord.dismiss()
        }
    }

    private fun handleChooseButton(item: NTDModelGuitar, index: Int) {
        val time1 = System.currentTimeMillis()
        guitars[index].isSelected = !guitars[index].isSelected!!
        flag = if (guitars[index].isSelected!!) {
            item.id!!
        } else {
            -1
        }
        for (i in 0 until guitars.size) {
            if (i != index) {
                guitars[i].isSelected = false
            }
        }
        pianoAdapter.setListGuitar(guitars)
        val time2 = System.currentTimeMillis()
        Log.d("Check_Time_Loop", "${time2 - time1}ms")
        hideImage()

        when (flag) {
            NTDHelperGuitarHelper.flagAm -> {
                binding.buttonAm1.visibility = View.VISIBLE
                binding.buttonAm2.visibility = View.VISIBLE
                binding.buttonAm3.visibility = View.VISIBLE
                disableLine1()
            }

            NTDHelperGuitarHelper.flagC -> {
                binding.buttonC1.visibility = View.VISIBLE
                binding.buttonC3.visibility = View.VISIBLE
                binding.buttonC2.visibility = View.VISIBLE
                disableLine1()
            }

            NTDHelperGuitarHelper.flagB -> {
                disableLine1()
            }

            NTDHelperGuitarHelper.flagDm -> {
                binding.buttonDm1.visibility = View.VISIBLE
                binding.buttonDm2.visibility = View.VISIBLE
                binding.buttonDm3.visibility = View.VISIBLE
                binding.buttonDm4.visibility = View.VISIBLE
                disableLine1And2()
            }

            NTDHelperGuitarHelper.flagG -> {
                binding.buttonG1.visibility = View.VISIBLE
                binding.buttonG2.visibility = View.VISIBLE
                binding.buttonG3.visibility = View.VISIBLE
                enableAllLine()
            }

            NTDHelperGuitarHelper.flagF -> {
                binding.buttonF1.visibility = View.VISIBLE
                binding.buttonF2.visibility = View.VISIBLE
                binding.buttonF3.visibility = View.VISIBLE
                binding.buttonF4.visibility = View.VISIBLE
            }

            else -> {
                enableAllLine()
            }
        }
    }

    private fun disableLine1() {
        binding.lineGuitar1.isActivated = false
        binding.lineGuitar2.isActivated = true

        binding.lineGuitar1.setImageResource(R.drawable.line_guitar_disable)
        binding.lineGuitar2.setImageResource(R.drawable.line_guitar)
    }

    private fun disableLine1And2() {
        binding.lineGuitar1.isActivated = false
        binding.lineGuitar2.isActivated = false

        binding.lineGuitar1.setImageResource(R.drawable.line_guitar_disable)
        binding.lineGuitar2.setImageResource(R.drawable.line_guitar_disable)
    }

    private fun enableAllLine() {
        binding.lineGuitar1.isActivated = true
        binding.lineGuitar2.isActivated = true
        binding.lineGuitar1.setImageResource(R.drawable.line_guitar)
        binding.lineGuitar2.setImageResource(R.drawable.line_guitar)
    }

    private fun getListImage() {
        images.add(binding.buttonAm1)
        images.add(binding.buttonAm2)
        images.add(binding.buttonAm3)
        images.add(binding.buttonC1)
        images.add(binding.buttonC2)
        images.add(binding.buttonC3)
        images.add(binding.buttonDm1)
        images.add(binding.buttonDm2)
        images.add(binding.buttonDm3)
        images.add(binding.buttonDm4)
        images.add(binding.buttonG1)
        images.add(binding.buttonG2)
        images.add(binding.buttonG3)
        images.add(binding.buttonF1)
        images.add(binding.buttonF2)
        images.add(binding.buttonF3)
        images.add(binding.buttonF4)
    }

    private fun hideImage() {
        for (item in images) {
            item.visibility = View.INVISIBLE
        }
    }

    private fun record() {
        if (binding.btnRecord.isActivated) {
            stopRecord()
        } else {
            TimerManager.startTimer(this)
            binding.btnRecord.isSelected = true
            TimerManager.setTimeCounting(true)
            recordMic()
        }
    }

    private fun recordMic() {
        val isAcceptPermission =
            NTDOtherPianoHelper.checkPermissionRecordAudio(this@NTDViewGuitarActivity) and NTDOtherPianoHelper.checkPermissionStorage(
                this@NTDViewGuitarActivity
            )
        if (isAcceptPermission) {
            isRecording = true
            binding.btnRecord.isActivated = true
            handleRecord()
        } else {
            startActivity(Intent(this@NTDViewGuitarActivity, NTDViewPermissionActivity::class.java))
            isRecording = false
        }
    }

    private fun handleRecord() {
        Log.d("Start_Record", "Record started")
        var file: File
        try {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder.setAudioChannels(1)
            mediaRecorder.setAudioEncodingBitRate(128000)
            mediaRecorder.setAudioSamplingRate(48000)
            val filesDir = filesDir
            val file2 = File(filesDir, "/" + System.currentTimeMillis() + ".aac")
            file2.also { file = it }
            if (!file2.exists()) {
                file.createNewFile()
            }
            Intrinsics.checkNotNull(file)
            mediaRecorder.setOutputFile(file.path)
            mediaRecorder.prepare()
            mediaRecorder.start()

            binding.btnRecord.isActivated = true
            fileCurrent = file
            return
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            Log.e("Error_Record_File", "${e.message}")
        } catch (e2: java.lang.Exception) {
            Log.e("Error_Record_File", "${e2.message}")
            e2.printStackTrace()
        }
    }

    private fun stopRecord() {
        binding.btnRecord.isSelected = false
        binding.btnRecord.isActivated = false
        dialogSaveRecord.show()
        dialogSaveRecord.setCanceledOnTouchOutside(false)
        TimerManager.stopTimer()
        timeTotal = TimerManager.timeTotal()
        TimerManager.setTimeCounting(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool!!.release()
    }

    override fun onPause() {
        if (isRecording) {
            mediaRecorder.stop()
            stopRecord()
        } else super.onPause()
        dialogRecordSuccess.binding.layoutRecordSaved.setOnClickListener { super.onPause() }
        dialogRecordSuccess.setOnCancelListener { super.onPause() }
        dialogSaveRecord.binding.tvClose.setOnClickListener {
            isRecording = false
            super.onPause()
        }
    }

    override fun onBackPressed() {
        if (isRecording) {
            mediaRecorder.stop()
            stopRecord()
        } else {
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
        dialogSaveRecord.binding.tvClose.setOnClickListener {
            isRecording = false
            onBackPressed()
        }
        dialogRecordSuccess.binding.layoutRecordSaved.setOnClickListener { onBackPressed() }
        dialogRecordSuccess.setOnCancelListener { onBackPressed() }
    }

    override fun onTimerTick(time: String) {
        runOnUiThread {
            binding.tvTime.text = time
        }
    }

    override fun onTimerFinish() {
        binding.tvTime.text = "00:00"
    }
}
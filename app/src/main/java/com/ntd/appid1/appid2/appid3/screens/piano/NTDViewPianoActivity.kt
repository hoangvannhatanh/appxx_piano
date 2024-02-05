package com.ntd.appid1.appid2.appid3.screens.piano

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.ntd.appid1.appid2.appid3.NTDViewMainActivity
import com.ntd.appid1.appid2.appid3.piano_lib.entity.NTDOtherAutoPlayEntity
import com.ntd.appid1.appid2.appid3.piano_lib.entity.NTDOtherPiano
import com.ntd.appid1.appid2.appid3.piano_lib.listener.NTDOtherOnLoadAudioListener
import com.ntd.appid1.appid2.appid3.piano_lib.listener.NTDOtherOnPianoAutoPlayListener
import com.ntd.appid1.appid2.appid3.piano_lib.listener.NTDOtherOnPianoListener
import com.ntd.appid1.appid2.appid3.piano_lib.view.NTDOtherPianoView
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd
import com.ntd.appid1.appid2.appid3.local.NTDHelperSharePrefUtils
import com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDB
import com.ntd.appid1.appid2.appid3.databinding.ActivityPianoBinding
import com.ntd.appid1.appid2.appid3.databinding.DialogRecordSavedBinding
import com.ntd.appid1.appid2.appid3.databinding.DialogSaveRecordBinding
import com.ntd.appid1.appid2.appid3.extensions.onAvoidDoubleClick
import com.ntd.appid1.appid2.appid3.helper.PianoPlaylist
import com.ntd.appid1.appid2.appid3.model.NTDModelPianoKey
import com.ntd.appid1.appid2.appid3.model.NTDPiano
import com.ntd.appid1.appid2.appid3.screens.permission.NTDViewPermissionActivity
import com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherPianoSettingDialog
import com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherPlayListDialog
import com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherRecordSuccessDialog
import com.ntd.appid1.appid2.appid3.screens.piano.dialog.NTDOtherSaveRecordDialog
import com.ntd.appid1.appid2.appid3.screens.piano.piano_style.NTDViewPianoStyleActivity
import com.ntd.appid1.appid2.appid3.screens.saxophone.TimerListener
import com.ntd.appid1.appid2.appid3.screens.saxophone.TimerManager
import com.ntd.appid1.appid2.appid3.utils.Constants
import com.ntd.appid1.appid2.appid3.utils.Network
import java.io.File
import java.util.Date
import kotlin.jvm.internal.Intrinsics

@SuppressLint("SetTextI18n")
class NTDViewPianoActivity : NTDOtherBaseActivityAppntd<ActivityPianoBinding>(),
    OnSeekBarChangeListener, NTDOtherOnPianoAutoPlayListener, NTDOtherOnLoadAudioListener,
    NTDOtherOnPianoListener {

    private lateinit var dialogSaveRecord: NTDOtherBaseDialogAppntd<DialogSaveRecordBinding>
    private lateinit var dialogPlayList: Dialog
    private lateinit var dialogSettingPiano: Dialog
    private lateinit var dialogRecordSuccess: NTDOtherBaseDialogAppntd<DialogRecordSavedBinding>
    private var isPlay = false
    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false
    private var isRecordingMic = false
    private lateinit var fileCurrent: File
    private var isRecordingPiano = false
    private var flagStopRecord = false
    private var listRecordPiano: MutableList<NTDModelPianoKey> = mutableListOf()
    private var flagInit = false
    private var listMusic: ArrayList<NTDOtherAutoPlayEntity> = arrayListOf()
    private var arrayList: ArrayList<NTDOtherAutoPlayEntity> = arrayListOf()
    private var pianoTempo: Int = 0
    private var isStopMedia: Boolean = false
    private var isBackScreen: Boolean = false
    private var isHide: Boolean = false
    private var sizePiano: Int = 0

    companion object {
        var mScale: Float = 1f
        var openFromStyle = false
    }

    override fun setViewBinding(): ActivityPianoBinding {
        return ActivityPianoBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        init()

        sizePiano = NTDHelperSharePrefUtils.getInt(NTDHelperSharePrefUtils.PIANO_KEY_SIZE, 0)
        //update ui increase and decrease button
        updateChangeSizeButton()
    }

    override fun initData() {
        binding.tvSongName.text = "Happy birthday"
        listMusic = PianoPlaylist.getHPBDSong()

        arrayList.clear()
        arrayList.addAll(listMusic)

        pianoTempo = NTDHelperSharePrefUtils.getInt(
            NTDHelperSharePrefUtils.PIANO_TEMPO, 15
        )

        //set break time of piano
        when (pianoTempo) {
            in 0 until 10 -> {
                for (item in arrayList) {
                    item.currentBreakTime *= 3
                }
            }

            in 10 until 20 -> {
                for (item in arrayList) {
                    item.currentBreakTime *= 2
                }
            }

            else -> {
                for (item in arrayList) {
                    item.currentBreakTime *= 1
                }
            }
        }
    }

    override fun initListener() {
        binding.apply {
            pv.setAutoPlayListener(this@NTDViewPianoActivity)
            pv.setLoadAudioListener(this@NTDViewPianoActivity)
            pv.setPianoListener(this@NTDViewPianoActivity)
            sb.setOnSeekBarChangeListener(this@NTDViewPianoActivity)

            dialogSaveRecord.binding.tvClose.setOnClickListener {
                clearRecordVar()
                dialogSaveRecord.dismiss()
                if (isBackScreen) {
                    finish()
                }
            }

            btnRecord.setOnClickListener {
                if (!flagInit) {
                    record()
                }
            }

            btnSetting.setOnClickListener {
                dialogSettingPiano = NTDOtherPianoSettingDialog(this@NTDViewPianoActivity) {
                    initView()
                    initData()
                    initListener()
                    pv.invalidate()
                }

                if (!isPlay) {
                    dialogSettingPiano.show()
                }
            }

            btnPlaySong.setOnClickListener {
                if (isPlay) {
                    isPlay = false
                    btnPlaySong.setImageResource(R.drawable.ic_play)
                    pv.autoPlayHandler.sendEmptyMessage(1)
                } else {
                    isPlay = true
                    btnPlaySong.setImageResource(R.drawable.ic_pause)

                    pv.autoPlay(
                        arrayList, sb, pianoTempo, seekBarSpeed
                    )
                }
            }

            btnBack.setOnClickListener {
                if (openFromStyle) {
                    var count = NTDHelperSharePrefUtils.getInt(
                        NTDHelperSharePrefUtils.IS_BACK_FROM_INSTRUMENT, 0
                    )
                    Log.d("uiiiiii", count.toString())
                    if (!flagInit) {
                        if (isRecording) {
                            isBackScreen = true
                            record()
                        } else {
                            if (!NTDHelperSharePrefUtils.getBoolean(
                                    NTDHelperSharePrefUtils.IS_RATED, false
                                )
                            ) {
                                count++
                                NTDHelperSharePrefUtils.setInt(
                                    NTDHelperSharePrefUtils.IS_BACK_FROM_INSTRUMENT, count
                                )
                                if (count % 2 == 0) {
                                    startActivity(
                                        Intent(
                                            this@NTDViewPianoActivity,
                                            NTDViewMainActivity::class.java
                                        )
                                    )
                                    finishAffinity()
                                    NTDViewMainActivity.showRateFromInstrument = true
                                    openFromStyle = false
                                } else {
                                    startActivity(
                                        Intent(
                                            this@NTDViewPianoActivity,
                                            NTDViewMainActivity::class.java
                                        )
                                    )
                                    finishAffinity()
                                    openFromStyle = false
                                }
                            } else {
                                startActivity(
                                    Intent(
                                        this@NTDViewPianoActivity, NTDViewMainActivity::class.java
                                    )
                                )
                                finishAffinity()
                                openFromStyle = false
                            }
                        }
                    }
                } else {
                    onBackPressed()
                }
            }

            btnDecrease.setOnClickListener {
                sizePiano--
                NTDHelperSharePrefUtils.putInt(NTDHelperSharePrefUtils.PIANO_KEY_SIZE, sizePiano)

                //update ui increase and decrease button
                updateChangeSizeButton()
                NTDOtherPianoView.reDraw = true
                pv.invalidate()
            }

            btnIncrease.setOnClickListener {
                sizePiano++
                NTDHelperSharePrefUtils.putInt(NTDHelperSharePrefUtils.PIANO_KEY_SIZE, sizePiano)

                //update ui increase and decrease button
                updateChangeSizeButton()
                NTDOtherPianoView.reDraw = true
                pv.invalidate()
            }

            btnListSong.setOnClickListener {
                dialogPlayList = NTDOtherPlayListDialog(this@NTDViewPianoActivity) { index ->
                    if (isPlay) {
                        isPlay = false
                        btnPlaySong.setImageResource(R.drawable.ic_play)
                        pv.autoPlayHandler.sendEmptyMessage(1)
                    }

                    listMusic = when (index) {
                        0 -> {
                            tvSongName.text = "Happy birthday"
                            PianoPlaylist.getHPBDSong()
                        }

                        1 -> {
                            tvSongName.text = "Jingle bell"
                            PianoPlaylist.getJingleBellSong()
                        }

                        2 -> {
                            tvSongName.text = "Little star"
                            PianoPlaylist.getLittleStarSong()
                        }

                        3 -> {
                            tvSongName.text = "Last Christmas"
                            PianoPlaylist.getLastChristmasSong()
                        }

                        5 -> {
                            tvSongName.text = "Beethoven 5th Symphony"
                            PianoPlaylist.getBee5thSymSong()
                        }

                        4 -> {
                            tvSongName.text = "All Of Me"
                            PianoPlaylist.getAllOfMeSong()
                        }

                        6 -> {
                            tvSongName.text = "Let It Go"
                            PianoPlaylist.getLetItGoSong()
                        }

                        7 -> {
                            tvSongName.text = "Call Me Maybe"
                            PianoPlaylist.getCallMeMaybeSong()
                        }

                        8 -> {
                            tvSongName.text = "Kiss the rain"
                            PianoPlaylist.getKissTheRainSong()
                        }

                        else -> {
                            tvSongName.text = "Happy birthday"
                            PianoPlaylist.getHPBDSong()
                        }
                    }

                    arrayList.clear()
                    arrayList.addAll(listMusic)

                    //set break time of piano
                    when (pianoTempo) {
                        in 0 until 10 -> {
                            for (item in arrayList) {
                                item.currentBreakTime *= 3
                            }
                        }

                        in 10 until 20 -> {
                            for (item in arrayList) {
                                item.currentBreakTime *= 2
                            }
                        }

                        else -> {
                            for (item in arrayList) {
                                item.currentBreakTime *= 1
                            }
                        }
                    }
                }

                if (!isPlay) {
                    dialogPlayList.show()
                }
            }

            btnStyle.onAvoidDoubleClick {
                if (!isPlay && !isRecording) {
                    startActivity(
                        Intent(
                            this@NTDViewPianoActivity, NTDViewPianoStyleActivity::class.java
                        )
                    )
                }
            }

            btnTwoPlayer.onAvoidDoubleClick {
                if (!isPlay && !isRecording) {
                    startActivity(
                        Intent(
                            this@NTDViewPianoActivity, NTDViewPianoTwoPlayerActivity::class.java
                        )
                    )
                }
            }

            btnHide.setOnClickListener {
                isHide = !isHide

                if (isHide) {
                    layoutTop.visibility = GONE
                    icHide.setImageResource(R.drawable.ic_hide_selected)
                } else {
                    layoutTop.visibility = VISIBLE
                    icHide.setImageResource(R.drawable.ic_hide)
                }
                NTDOtherPianoView.reDraw = true
                Log.d(
                    "PianoActivity", "initListener===: measureHeight " + layoutTop.measuredHeight
                )
            }

            btnDoubleKey.onAvoidDoubleClick {
                if (!isPlay && !isRecording) {
                    startActivity(
                        Intent(
                            this@NTDViewPianoActivity, NTDViewDoubleKeyActivity::class.java
                        )
                    )
                }
            }
        }
    }

    private fun init() {
        setStylePiano()

        // set show note piano
        NTDOtherPianoView.isShowNote = NTDHelperSharePrefUtils.getBoolean(
            NTDHelperSharePrefUtils.PIANO_SHOW_NOTE, false
        )

        binding.pv.setSoundPollMaxStream(10)
        binding.pv.setCorD0(true)

        dialogSaveRecord = NTDOtherSaveRecordDialog(this) { fileName ->
            if (isRecordingMic and isRecording) {

                NTDOtherRecordDB.getInstance(this@NTDViewPianoActivity).recordDAO().insertRecord(
                    com.ntd.appid1.appid2.appid3.database.NTDOtherRecord(
                        id = null,
                        fileName = fileName,
                        filePath = fileCurrent.path,
                        durationTime = TimerManager.timeTotal(),
                        createdAt = Date().time
                    )
                )
                isRecording = false
                isRecordingMic = false
                isRecordingPiano = false

                clearRecordVar()

            }
            flagStopRecord = true

            dialogRecordSuccess = NTDOtherRecordSuccessDialog(this)
            dialogRecordSuccess.show()

        }

        if (mediaRecorder == null) {
            mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MediaRecorder(this@NTDViewPianoActivity)
            } else {
                MediaRecorder()
            }
        }
    }

    private fun updateChangeSizeButton() {
        binding.apply {
            btnDecrease.isEnabled = (sizePiano != Constants.DEFAULT_SIZE).also {
                btnDecrease.setImageResource(if (it) R.drawable.ic_subtraction else R.drawable.ic_subtraction_disable)
            }

            btnIncrease.isEnabled = (sizePiano != Constants.BIG_SIZE_5).also {
                btnIncrease.setImageResource(if (it) R.drawable.ic_plus else R.drawable.ic_plus_disable)
            }
        }

    }

    private fun setStylePiano() {
        val stylePiano = NTDHelperSharePrefUtils.getInt(NTDHelperSharePrefUtils.STYLE_PIANO, 0)
        val myPiano: NTDPiano = NTDOtherPianoHelper.getStyles()[stylePiano]
        NTDOtherPiano.blackDrawable = myPiano.blackKey!!
        NTDOtherPiano.whiteDrawable = myPiano.whiteKey!!
        binding.layoutPianoControl.setBackgroundResource(myPiano.backgroundHeader!!)
        binding.layoutParent.setBackgroundResource(myPiano.backgroundLayout!!)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        binding.pv.scroll(progress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    override fun onPianoAutoPlayStart() {
        isPlay = true
        binding.btnPlaySong.setImageResource(R.drawable.ic_pause)
        binding.btnPlaySong.setBackgroundResource(R.drawable.bg_btn_purple)
    }

    override fun onPianoAutoPlayEnd() {
        isPlay = false
        binding.btnPlaySong.setImageResource(R.drawable.ic_play)
        binding.btnPlaySong.setBackgroundResource(R.drawable.bg_btn_instrument)
    }

    override fun loadPianoAudioStart() {
        flagInit = true
        binding.layoutLoading.visibility = VISIBLE
    }

    override fun loadPianoAudioFinish() {
        mScale = binding.pv.scale
        flagInit = false
        binding.layoutLoading.visibility = GONE

    }

    override fun loadPianoAudioError(e: Exception?) {}

    override fun loadPianoAudioProgress(progress: Int) {}

    private fun record() {
        if (binding.btnRecord.isActivated) { // dang ghi am
            // thuc hien dung ghi am
            binding.btnRecord.isActivated = false
            flagStopRecord = false
            dialogSaveRecord.show()
            binding.btnRecord.setImageResource(R.drawable.ic_record)
            binding.llTimeRecord.isSelected = false
            TimerManager.stopTimer()
            if (!isStopMedia) {
                mediaRecorder?.stop()
                isStopMedia = true
            }
        } else { // chua ghi am
            Log.d("Check_Recording", "Not Recording")
            recordMic()
        }
    }

    private fun recordMic() {
        val isAcceptPermission =
            NTDOtherPianoHelper.checkPermissionRecordAudio(this@NTDViewPianoActivity) and NTDOtherPianoHelper.checkPermissionStorage(
                this@NTDViewPianoActivity
            )
        if (isAcceptPermission) { // thuc hien ghi am
            flagStopRecord = false
            isRecordingMic = true
            isRecording = true
            isRecordingPiano = false
            binding.btnRecord.isActivated = true
            binding.btnRecord.setImageResource(R.drawable.ic_stop_recording)
            binding.llTimeRecord.isSelected = true
            handleRecord()
        } else {
            startActivity(Intent(this@NTDViewPianoActivity, NTDViewPermissionActivity::class.java))
            clearRecordVar()
        }
    }

    private fun handleRecord() {
        Log.d("Start_Record", "Record started")
        var file: File
        try {
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder?.setAudioChannels(1)
            mediaRecorder?.setAudioEncodingBitRate(128000)
            mediaRecorder?.setAudioSamplingRate(48000)
            val filesDir = filesDir
            val file2 = File(filesDir, "/" + System.currentTimeMillis() + ".aac")
            file2.also { file = it }
            if (!file2.exists()) {
                file.createNewFile()
            }
            Intrinsics.checkNotNull(file)
            mediaRecorder?.setOutputFile(file.path)
            mediaRecorder?.prepare()
            mediaRecorder?.start()

            TimerManager.startTimer(object : TimerListener {
                override fun onTimerTick(time: String) {
                    binding.tvTimeRecord.text = time
                }

                override fun onTimerFinish() {
                    binding.tvTimeRecord.text = "00:00"
                }

            })

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

    override fun onPause() {
        super.onPause()
        if (isRecording && isRecordingMic) {
            dialogSaveRecord.show()
            TimerManager.stopTimer()
            binding.llTimeRecord.isSelected = false
            binding.btnRecord.isActivated = false
            flagStopRecord = true
            if (!isStopMedia) {
                mediaRecorder?.stop()
                isStopMedia = true
            }
        }

        if (isPlay) {
            binding.pv.stopAutoPlay()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRecording) {
            isRecording = false
            isRecordingPiano = false
            isRecordingMic = false
        }
        binding.pv.releaseAutoPlay()
    }

    override fun onResume() {
        super.onResume()
        isPlay = false

    }

    override fun onBackPressed() {
        if (!flagInit) {
            if (isRecording) {
                isBackScreen = true
                record()
            } else {
                finish()
            }
        } else {
            return
        }
    }

    override fun onPianoInitFinish() {}
    override fun onPianoClick(
        type: NTDOtherPiano.PianoKeyType?,
        voice: NTDOtherPiano.PianoVoice?,
        group: Int,
        positionOfGroup: Int
    ) {
        if (isRecordingPiano) {
            listRecordPiano.add(
                NTDModelPianoKey(
                    type!!.value, group, positionOfGroup, System.currentTimeMillis()
                )
            )
        } else {
            listRecordPiano.clear()
        }
    }


    private fun clearRecordVar() {
        isRecording = false
        isRecordingPiano = false
        isRecordingMic = false
        flagStopRecord = true
    }
}
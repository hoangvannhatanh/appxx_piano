package com.ntd.appid1.appid2.appid3.screens.record

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseActivityAppntd
import com.ntd.appid1.appid2.appid3.database.NTDOtherRecord
import com.ntd.appid1.appid2.appid3.database.NTDOtherRecordDB
import com.ntd.appid1.appid2.appid3.databinding.ActivityRecordDetailBinding
import com.ntd.appid1.appid2.appid3.utils.NTDOtherMyDialog
import java.io.File
import java.io.IOException

class NTDViewRecordDetail : NTDOtherBaseActivityAppntd<ActivityRecordDetailBinding>() {
    private lateinit var dialogChangeName: Dialog
    private lateinit var dialogDeleteRecord: Dialog
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var mItem: NTDOtherRecord
    private var countDownTimer: CountDownTimer? = null

    private var currentTimerPosition: Long = 0L
    private val db = NTDOtherRecordDB.getInstance(this@NTDViewRecordDetail)
    private var isStarted: Boolean = false
    private var handler: Handler = Handler()
    override fun setViewBinding(): ActivityRecordDetailBinding {
        return ActivityRecordDetailBinding.inflate(LayoutInflater.from(this))
    }

    override fun initView() {
        dialogChangeName =
            NTDOtherMyDialog.getDialogRecord(this@NTDViewRecordDetail, R.layout.dialog_change_name)
        dialogChangeName.setCanceledOnTouchOutside(false)
        dialogDeleteRecord = NTDOtherMyDialog.getDialogRecord(
            this@NTDViewRecordDetail,
            R.layout.dialog_delete_record
        )
        dialogDeleteRecord.setCanceledOnTouchOutside(false)
        binding.ivPlay.setImageResource(R.drawable.ic_play_record)
        initData()
    }

    override fun initData() {
        if (intent.extras != null) {
            mItem = intent.extras?.getSerializable("KEY_DETAIL") as NTDOtherRecord
            binding.tvTitle.text = mItem.fileName
            binding.ivPlay.isEnabled = true
        } else {
            binding.ivPlay.isEnabled = false
            binding.tvTitle.text = "null"
        }
        mediaPlayer = MediaPlayer()
    }

    override fun initListener() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.ivShare.setOnClickListener {
            binding.ivShare.isEnabled = false
            handler.postDelayed({ binding.ivShare.isEnabled = true }, 500)
            val file = File(mItem.filePath)
            val fileUri: Uri = FileProvider.getUriForFile(
                this@NTDViewRecordDetail,
                "com.ntd.appid1.appid2.appid3.contentprovider",
                file
            )
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "audio/*"
            shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(shareIntent, "Share File"))
        }

        binding.btnDelete.setOnClickListener {
            deleteRecord(mItem)
        }

        binding.ivPlay.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                countDownTimer?.cancel()
                binding.ivPlay.setImageResource(R.drawable.ic_play_record)
            } else {
                handlePlayMusicFile(mItem)
            }
        }
        binding.ivRename.setOnClickListener {
            changeNewName(mItem)
        }
        binding.tvTime.text = convertLongToFormattedTime(mItem.durationTime)
    }

    private fun handlePlayMusicFile(item: NTDOtherRecord) {
        Log.d("Check_Path_File", item.filePath)
        try {
            if (!isStarted) {
                isStarted = true
                mediaPlayer = MediaPlayer()
                mediaPlayer?.setDataSource(item.filePath)
                mediaPlayer?.prepare()

                setCountDownTimer(mItem.durationTime)
                countDownTimer?.start()

                mediaPlayer?.setOnCompletionListener {
                    mediaPlayer?.stop()
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer()
                    mediaPlayer?.setDataSource(item.filePath)
                    mediaPlayer?.prepare()
                    binding.ivPlay.setImageResource(R.drawable.ic_play_record)
                    isStarted = false
                }
                binding.ivPlay.setImageResource(R.drawable.ic_pause_record)
                mediaPlayer?.start()
            } else {
                setCountDownTimer(mItem.durationTime - currentTimerPosition)
                binding.ivPlay.setImageResource(R.drawable.ic_pause_record)
                countDownTimer?.start()
                mediaPlayer?.start()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                it.release()
            }
        }
        if (countDownTimer != null) {
            countDownTimer?.onFinish()
        }
    }

    private fun setCountDownTimer(duration: Long) {
        countDownTimer = object : CountDownTimer(duration, 1000) {
            override fun onTick(p0: Long) {
                currentTimerPosition += 1000
                binding.tvTime.text = convertLongToFormattedTime(p0)
            }

            override fun onFinish() {
                binding.tvTime.text = "00:00:00"
                currentTimerPosition = 0
            }
        }
    }

    private fun changeNewName(item: NTDOtherRecord) {
        dialogChangeName.show()
        val input = dialogChangeName.findViewById<EditText>(R.id.inputFileName)
        val iconClose = dialogChangeName.findViewById<TextView>(R.id.btnClose)
        val textSave = dialogChangeName.findViewById<TextView>(R.id.btnSave)

        input.setText(item.fileName)

        iconClose.setOnClickListener {
            dialogChangeName.cancel()
            input.text.clear()
        }

        textSave.setOnClickListener {
            val newName = input.text.trim().toString()
            item.fileName = newName
            if (newName.isEmpty()) {
                Toast.makeText(
                    this@NTDViewRecordDetail,
                    resources.getString(R.string.message_must_not_be_null),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                db.recordDAO().updateRecord(item)
                dialogChangeName.cancel()
                input.text.clear()
                binding.tvTitle.text = newName
            }
        }
    }

    private fun deleteRecord(item: NTDOtherRecord) {
        dialogDeleteRecord.show()
        val btnCancel = dialogDeleteRecord.findViewById<TextView>(R.id.btnCancel)
        val btnDelete = dialogDeleteRecord.findViewById<TextView>(R.id.btnDelete)

        btnCancel.setOnClickListener {
            dialogDeleteRecord.cancel()
        }

        btnDelete.setOnClickListener {
            db.recordDAO().deleteRecord(item)
            onBackPressed()
        }
    }

    private fun convertLongToFormattedTime(time: Long): String {
        val seconds = time / 1000 % 60
        val minutes = time / (1000 * 60) % 60
        val hours = time / (1000 * 60 * 60) % 24

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
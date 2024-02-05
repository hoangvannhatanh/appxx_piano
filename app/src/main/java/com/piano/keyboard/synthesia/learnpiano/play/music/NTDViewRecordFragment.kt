package com.piano.keyboard.synthesia.learnpiano.play.music

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.piano.keyboard.synthesia.learnpiano.play.music.adapter.NTDOtherRecordsAdapter
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseFragmentAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.database.NTDOtherRecord
import com.piano.keyboard.synthesia.learnpiano.play.music.database.NTDOtherRecordDB
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.FragmentRecordBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ItemRecordBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.screens.record.NTDViewRecordDetail
import java.util.Calendar

class NTDViewRecordFragment : NTDOtherBaseFragmentAppntd<FragmentRecordBinding>() {
    override fun setViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentRecordBinding {
        return FragmentRecordBinding.inflate(inflater, viewGroup, false)
    }

    private lateinit var list: MutableList<NTDOtherRecord>
    private lateinit var recordsAdapter: NTDOtherRecordsAdapter
    private lateinit var db: NTDOtherRecordDB

    override fun initView() {
        init()
    }

    override fun viewListener() {}

    fun initData() {
    }

    private fun init() {
        db = NTDOtherRecordDB.getInstance(requireContext())
        list = mutableListOf()
        list = db.recordDAO().getAllRecord() as MutableList<NTDOtherRecord>
        list.sortBy { it.id }
        list.reverse()

        recordsAdapter = NTDOtherRecordsAdapter(requireContext(), list, object : NTDOtherRecordsAdapter.ClickItem {

            override fun more(index: Int, binding: ItemRecordBinding) {
                if (!list[index].filePath.contains("Sample")) {
                    startActivity(Intent(requireContext(), NTDViewRecordDetail::class.java).apply {
                        putExtra("KEY_DETAIL", list[index])
                    })
                } else {
                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.message_this_is_sample),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        binding.rcv.apply {
            adapter = recordsAdapter
        }

        binding.layoutNoRecord.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onPause() {
        super.onPause()
        recordsAdapter.setListRecord(list)
    }

    override fun onResume() {
        super.onResume()
        list = db.recordDAO().getAllRecord() as MutableList<NTDOtherRecord>

        if (list.isEmpty()) {
            list.add(0, NTDOtherRecord(
                id = null,
                fileName = "File Sample",
                filePath = "SamplePath",
                durationTime = 0L,
                createdAt = Calendar.getInstance().timeInMillis)
            )
        }
        recordsAdapter.setListRecord(list)
        binding.rcv.swapAdapter(recordsAdapter, false)
        binding.rcv.recycledViewPool.clear()
        binding.layoutNoRecord.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }
}
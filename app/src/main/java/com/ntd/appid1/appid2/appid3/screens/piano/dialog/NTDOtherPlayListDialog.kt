package com.ntd.appid1.appid2.appid3.screens.piano.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd
import com.ntd.appid1.appid2.appid3.databinding.DialogSongListBinding
import com.ntd.appid1.appid2.appid3.screens.piano.NTDOtherPianoHelper
import com.ntd.appid1.appid2.appid3.screens.piano.adapter.NTDOtherPlayListAdapter

class NTDOtherPlayListDialog(ctx: Context, val onClick: (index: Int) -> Unit) :
    NTDOtherBaseDialogAppntd<DialogSongListBinding>(ctx) {
    override fun setViewBinding(context: Context?): DialogSongListBinding {
        return DialogSongListBinding.inflate(LayoutInflater.from(context))
    }

    override fun initView(context: Context?) {
        binding.rcvSongList.apply {
            layoutManager = LinearLayoutManager(context)
            context?.let {
                adapter = NTDOtherPlayListAdapter(NTDOtherPianoHelper.getSongs()) {
                    onClick(it)
                    dismiss()
                }
            }

            //click item default
//            onClick(0)
        }

        binding.imgClose.setOnClickListener { dismiss() }
    }
}
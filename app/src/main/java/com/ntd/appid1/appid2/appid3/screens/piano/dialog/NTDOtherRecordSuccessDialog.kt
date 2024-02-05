package com.ntd.appid1.appid2.appid3.screens.piano.dialog

import android.content.Context
import android.view.LayoutInflater
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd
import com.ntd.appid1.appid2.appid3.databinding.DialogRecordSavedBinding

class NTDOtherRecordSuccessDialog(ctx: Context) : NTDOtherBaseDialogAppntd<DialogRecordSavedBinding>(ctx) {
    override fun setViewBinding(context: Context?): DialogRecordSavedBinding {
        return DialogRecordSavedBinding.inflate(LayoutInflater.from(context))
    }

    override fun initView(context: Context?) {
        binding.layoutRecordSaved.setOnClickListener { dismiss() }
    }

}
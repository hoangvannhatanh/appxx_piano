package com.piano.keyboard.synthesia.learnpiano.play.music.screens.piano.dialog

import android.content.Context
import android.view.LayoutInflater
import com.piano.keyboard.synthesia.learnpiano.play.music.base.NTDOtherBaseDialogAppntd
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.DialogRecordSavedBinding

class NTDOtherRecordSuccessDialog(ctx: Context) : NTDOtherBaseDialogAppntd<DialogRecordSavedBinding>(ctx) {
    override fun setViewBinding(context: Context?): DialogRecordSavedBinding {
        return DialogRecordSavedBinding.inflate(LayoutInflater.from(context))
    }

    override fun initView(context: Context?) {
        binding.layoutRecordSaved.setOnClickListener { dismiss() }
    }

}
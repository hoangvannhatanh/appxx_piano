package com.ntd.appid1.appid2.appid3.screens.piano.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.base.NTDOtherBaseDialogAppntd
import com.ntd.appid1.appid2.appid3.databinding.DialogSaveRecordBinding

class NTDOtherSaveRecordDialog(ctx: Context, val onClick: (name: String) -> Unit) : NTDOtherBaseDialogAppntd<DialogSaveRecordBinding>(ctx){
    override fun setViewBinding(context: Context?): DialogSaveRecordBinding {
        return DialogSaveRecordBinding.inflate(LayoutInflater.from(context))
    }

    override fun initView(context: Context?) {
        setCancelable(false)
        binding.apply {

            tvSave.setOnClickListener {
                if (edtNameRecord.text.toString().isBlank()) {
                    Toast.makeText(context, context?.getString(R.string.message_must_not_be_null), Toast.LENGTH_SHORT).show()
                } else {
                    onClick(edtNameRecord.text.toString().trim())
                    dismiss()
                    edtNameRecord.setText("")
                }
            }
        }
    }
}
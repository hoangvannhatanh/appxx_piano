package com.ntd.appid1.appid2.appid3.extensions

import android.view.View

fun View.onAvoidDoubleClick(
    throttleDelay: Long = 600,
    onClick: (View) -> Unit
) {
    setOnClickListener {
        onClick(this)
        isClickable = false
        postDelayed({ isClickable = true }, throttleDelay)
    }
}
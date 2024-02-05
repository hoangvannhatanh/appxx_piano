package com.piano.keyboard.synthesia.learnpiano.play.music.extensions

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
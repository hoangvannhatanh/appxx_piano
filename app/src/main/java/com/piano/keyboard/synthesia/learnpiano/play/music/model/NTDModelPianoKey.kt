package com.piano.keyboard.synthesia.learnpiano.play.music.model

data class NTDModelPianoKey(
    var type: Int,
    var group: Int,
    var position: Int,
    var currentTime: Long
) {
}
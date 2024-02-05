package com.piano.keyboard.synthesia.learnpiano.play.music.model

data class NTDModelMyPiano(
    var id: Int? = null,
    var buttonStart: Int? = null,
    var buttonPrevious: Int? = null,
    var buttonNext: Int? = null,
    var buttonEnd: Int? = null,
    var buttonRecord: Int? = null,
    var whiteKey: Int? = null,
    var blackKey: Int? = null,
    var background: Int? = null,
) {
}
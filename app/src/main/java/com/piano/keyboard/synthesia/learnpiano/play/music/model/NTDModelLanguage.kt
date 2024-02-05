package com.piano.keyboard.synthesia.learnpiano.play.music.model

data class NTDModelLanguage(
    var languageName: String,
    var isoLanguage: String,
    var isCheck: Boolean,
    var image: Int? = null
) {
    constructor() : this("", "", false, 0) {}
}
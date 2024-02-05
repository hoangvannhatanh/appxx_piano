package com.ntd.appid1.appid2.appid3.model

data class NTDModelLanguage(
    var languageName: String,
    var isoLanguage: String,
    var isCheck: Boolean,
    var image: Int? = null
) {
    constructor() : this("", "", false, 0) {}
}
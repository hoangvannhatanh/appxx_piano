package com.ntd.appid1.appid2.appid3.model

data class NTDModelSong(
    val id: Int,
    val name: String,
    val singleName: String
): Comparable<NTDModelSong> {
    override fun compareTo(other: NTDModelSong): Int {
        return name.compareTo(other.name)
    }

    override fun toString(): String {
        return name
    }
}
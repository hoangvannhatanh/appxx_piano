package com.piano.keyboard.synthesia.learnpiano.play.music.model

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
package com.piano.keyboard.synthesia.learnpiano.play.music.screens.saxophone

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.piano.keyboard.synthesia.learnpiano.play.music.R

object NTDHelperSaxophoneHelper {

    private var listMedia: MutableList<MediaPlayer> = mutableListOf()
    const val INDEX_BUTTON_0 = 0
    const val INDEX_BUTTON_1 = 1
    const val INDEX_BUTTON_2 = 2
    const val INDEX_BUTTON_3 = 3
    const val INDEX_BUTTON_4 = 4
    const val INDEX_BUTTON_5 = 5
    const val INDEX_BUTTON_6 = 6
    const val INDEX_BUTTON_7 = 7
    const val INDEX_BUTTON_8 = 8
    const val INDEX_BUTTON_9 = 9
    const val INDEX_BUTTON_10 = 10

    fun turnOffSong() {
        for (i in 0 until listMedia.size) {
            listMedia[i].stop()
        }
    }

    fun getListMedia(context: Context) {
        listMedia.clear()
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_01))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_02))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_03))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_04))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_05))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_06))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_07))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_08))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_09))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_10))
        listMedia.add(MediaPlayer.create(context, R.raw.saxo_11))
    }

    fun playSong(index: Int, context: Context) {
        val time1 = System.currentTimeMillis()

        listMedia[index].release()
        listMedia.removeAt(index)
        val sound: Int = when (index) {
            INDEX_BUTTON_0 -> {
                R.raw.saxo_01
            }

            INDEX_BUTTON_1 -> {
                R.raw.saxo_02
            }

            INDEX_BUTTON_2 -> {
                R.raw.saxo_03
            }

            INDEX_BUTTON_3 -> {
                R.raw.saxo_04
            }

            INDEX_BUTTON_4 -> {
                R.raw.saxo_05
            }

            INDEX_BUTTON_5 -> {
                R.raw.saxo_06
            }

            INDEX_BUTTON_6 -> {
                R.raw.saxo_07
            }

            INDEX_BUTTON_7 -> {
                R.raw.saxo_08
            }

            INDEX_BUTTON_8 -> {
                R.raw.saxo_09
            }

            INDEX_BUTTON_9 -> {
                R.raw.saxo_10
            }

            INDEX_BUTTON_10 -> {
                R.raw.saxo_11
            }

            else -> {
                R.raw.saxo_01
            }
        }
        listMedia.add(index, MediaPlayer.create(context, sound))
        listMedia[index].start()

        Log.d("Check_Time_Play_Sound_Saxophone", "${System.currentTimeMillis() - time1}ms")
    }
}
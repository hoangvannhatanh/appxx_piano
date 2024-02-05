package com.ntd.appid1.appid2.appid3.screens.piano

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.model.NTDModelMyPiano
import com.ntd.appid1.appid2.appid3.model.NTDModelSong
import com.ntd.appid1.appid2.appid3.model.NTDPiano
import com.ntd.appid1.appid2.appid3.model.NTDPianoStyle

object NTDOtherPianoHelper {
    fun getSongs(): MutableList<NTDModelSong> {
        val list: MutableList<NTDModelSong> = mutableListOf()

        list.add(NTDModelSong(0, "Happy birthday", "Stevie Wonder"))
        list.add(NTDModelSong(1, "Jingle bell", "James Lord Pierpont's"))
        list.add(NTDModelSong(2, "Little star", "Jane Taylor"))
        list.add(NTDModelSong(3, "Last Christmas", "Wham!"))
        list.add(NTDModelSong(4, "All Of Me", "Stevie Wonder"))
        list.add(NTDModelSong(5, "Beethoven 5th Symphony", "Beethoven"))
        list.add(NTDModelSong(6, "Let It Go", "Idina Menzel"))
        list.add(NTDModelSong(7, "Call Me Maybe", "Carly Rae Jepsen"))
        list.add(NTDModelSong(8, "Kiss The Rain", "Yiruma"))

        return list
    }

    fun getStyles(): MutableList<NTDPiano> {
        val listPiano: MutableList<NTDPiano> = mutableListOf()

        listPiano.add(NTDPiano(R.drawable.white_piano_key, R.drawable.black_piano_key, R.drawable.header_style_1, R.drawable.layout_style_1))
        listPiano.add(NTDPiano(R.drawable.white_piano_key_1, R.drawable.black_piano_key, R.drawable.header_style_1, R.drawable.layout_style_1))
        listPiano.add(NTDPiano(R.drawable.white_piano_key, R.drawable.black_piano_key, R.drawable.header_style_2, R.drawable.layout_style_2))
        listPiano.add(NTDPiano(R.drawable.white_piano_key_3, R.drawable.black_piano_key_3, R.drawable.header_style_3, R.drawable.layout_style_3))
        listPiano.add(NTDPiano(R.drawable.white_piano_key_4, R.drawable.black_piano_key_4, R.drawable.header_style_4, R.drawable.layout_style_4))
        listPiano.add(NTDPiano(R.drawable.white_piano_key, R.drawable.black_piano_key_5, R.drawable.header_style_5, R.drawable.layout_style_5))
        listPiano.add(NTDPiano(R.drawable.white_piano_key, R.drawable.black_piano_key_6, R.drawable.header_style_6, R.drawable.layout_style_6))
        listPiano.add(NTDPiano(R.drawable.white_piano_key_7, R.drawable.black_piano_key_7, R.drawable.header_style_7, R.drawable.layout_style_7))
        return listPiano
    }


    fun checkPermissionStorage(
        context: Context,
    ): Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            (ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_MEDIA_AUDIO
            ) == PackageManager.PERMISSION_GRANTED)
            return true
        } else {
            return (ContextCompat.checkSelfPermission(
                    context, Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    context, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(
                    context, Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    context, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED)
        }
    }

    fun checkPermissionRecordAudio(context: Context): Boolean =
        ContextCompat.checkSelfPermission(
            context, Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
}
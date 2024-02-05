package com.piano.keyboard.synthesia.learnpiano.play.music.screens.guitar

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import com.piano.keyboard.synthesia.learnpiano.play.music.R
import com.piano.keyboard.synthesia.learnpiano.play.music.model.NTDModelGuitar

object NTDHelperGuitarHelper {
    const val flagAm = 0
    const val flagC = 1
    const val flagB = 2
    const val flagE = 3
    const val flagDm = 4
    const val flagF = 5
    const val flagEm = 6
    const val flagG = 7

    fun loadAnimation(image: ImageView) {
        val upAnimator =
            ObjectAnimator.ofFloat(image, "translationY", 0f, -15f)
        upAnimator.duration = 25
        upAnimator.interpolator = AccelerateDecelerateInterpolator()
        val downAnimator =
            ObjectAnimator.ofFloat(image, "translationY", -15f, 0f)
        downAnimator.duration = 25
        downAnimator.interpolator = AccelerateDecelerateInterpolator()
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(upAnimator, downAnimator)
        animatorSet.duration = 50
        animatorSet.start()
    }

    fun getGuitar(): MutableList<NTDModelGuitar> {
        val list: MutableList<NTDModelGuitar> = mutableListOf()

        list.add(NTDModelGuitar(flagAm, R.drawable.state_button_am, false))
        list.add(NTDModelGuitar(flagC, R.drawable.state_button_c, false))
        list.add(NTDModelGuitar(flagB, R.drawable.state_button_bm, false))
        list.add(NTDModelGuitar(flagE, R.drawable.state_button_e, false))
        list.add(NTDModelGuitar(flagDm, R.drawable.state_button_dm, false))
        list.add(NTDModelGuitar(flagF, R.drawable.state_button_f, false))
        list.add(NTDModelGuitar(flagEm, R.drawable.state_button_em, false))
        list.add(NTDModelGuitar(flagG, R.drawable.state_button_g, false))

        return list
    }
}
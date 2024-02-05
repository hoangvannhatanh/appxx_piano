package com.piano.keyboard.synthesia.learnpiano.play.music.screens.piano.piano_style

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.LayoutItemPianoStyleBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.model.NTDPianoStyle

class NTDPianoStyleAdapter(private val listPianoStyles: MutableList<NTDPianoStyle>) :
    RecyclerView.Adapter<NTDPianoStyleAdapter.NTDPianoStyleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NTDPianoStyleViewHolder {
        return NTDPianoStyleViewHolder(
            LayoutItemPianoStyleBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listPianoStyles.size

    override fun onBindViewHolder(holder: NTDPianoStyleViewHolder, position: Int) {
        holder.onBind(listPianoStyles[position])
    }

    inner class NTDPianoStyleViewHolder(private val binding: LayoutItemPianoStyleBinding) :
        ViewHolder(binding.root) {
        fun onBind(item: NTDPianoStyle) {
            item.image?.let { binding.imgPianoStyle.setImageResource(it) }
        }
    }
}
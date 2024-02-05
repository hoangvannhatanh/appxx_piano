package com.piano.keyboard.synthesia.learnpiano.play.music.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ItemButtonGuitarBinding
import com.piano.keyboard.synthesia.learnpiano.play.music.model.NTDModelGuitar

class NTDOtherGuitarAdapter(
    private var context: Context,
    private var list: MutableList<NTDModelGuitar>,
    private var clickItem: ClickItem
): RecyclerView.Adapter<NTDOtherGuitarAdapter.PianoViewHolder>() {
    inner class PianoViewHolder(val binding: ItemButtonGuitarBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: NTDModelGuitar, index: Int) {
            val time1 = System.currentTimeMillis()
            binding.imageButtonPiano.setImageDrawable(ContextCompat.getDrawable(context, item.drawable!!))
            binding.imageButtonPiano.isActivated = item.isSelected!!
            val time2 = System.currentTimeMillis()
            Log.d("Check_Time_Adapter", "${time2 - time1}")

            // set event
            binding.imageButtonPiano.setOnClickListener {
                clickItem.chooseButton(item, index)
            }
        }
    }

    interface ClickItem {
        fun chooseButton(item: NTDModelGuitar, index: Int)
    }

    fun setListGuitar(list: MutableList<NTDModelGuitar>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PianoViewHolder =
        PianoViewHolder(
            ItemButtonGuitarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PianoViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item, position)
    }
}
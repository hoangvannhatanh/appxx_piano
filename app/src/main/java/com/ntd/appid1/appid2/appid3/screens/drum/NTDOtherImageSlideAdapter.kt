package com.ntd.appid1.appid2.appid3.screens.drum

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.databinding.ItemStyleDrumBinding

class NTDOtherImageSlideAdapter(var listImage: List<Int>, var context: Context) :
    RecyclerView.Adapter<NTDOtherImageSlideAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemStyleDrumBinding

        init {
            binding = ItemStyleDrumBinding.bind(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_style_drum, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemStyle.setImageDrawable(
            context.getDrawable(listImage[position])
        )
    }
}
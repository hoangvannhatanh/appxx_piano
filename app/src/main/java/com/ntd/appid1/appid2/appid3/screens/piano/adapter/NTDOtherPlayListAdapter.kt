package com.ntd.appid1.appid2.appid3.screens.piano.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ntd.appid1.appid2.appid3.databinding.LayoutItemSongPlaylistBinding
import com.ntd.appid1.appid2.appid3.model.NTDModelSong

class NTDOtherPlayListAdapter(
    private val listSong: MutableList<NTDModelSong>,
    val onClick: (position: Int) -> Unit
) : Adapter<NTDOtherPlayListAdapter.PlayListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val viewHolder = LayoutItemSongPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayListViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return listSong.size
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.binding.apply {
            tvSongName.text = listSong[position].name
        }

        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    inner class PlayListViewHolder(val binding: LayoutItemSongPlaylistBinding) : ViewHolder(binding.root)
}
package com.piano.keyboard.synthesia.learnpiano.play.music.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piano.keyboard.synthesia.learnpiano.play.music.database.NTDOtherRecord
import com.piano.keyboard.synthesia.learnpiano.play.music.databinding.ItemRecordBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NTDOtherRecordsAdapter (
    var context: Context,
    var list: MutableList<NTDOtherRecord>,
    var clickItem: ClickItem
): RecyclerView.Adapter<NTDOtherRecordsAdapter.RecordsViewHolder>() {
    inner class RecordsViewHolder(val binding: ItemRecordBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: NTDOtherRecord, index: Int) {
            if (item.fileName.length >= 25) {
                binding.textRecordName.text = item.fileName.substring(0, 25) + "..."
            } else {
                binding.textRecordName.text = item.fileName
            }
            binding.textDateTime.text = convertLongToFormattedDateTime(item.createdAt)
            binding.textTime.text = convertLongToFormattedTime(item.durationTime)
            binding.layoutContainer.setOnClickListener { clickItem.more(index, binding) }
        }

        private fun convertLongToFormattedDateTime(timeInMillis: Long): String {
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
            val dateTime = Date(timeInMillis)
            return dateFormat.format(dateTime)
        }

        private fun convertLongToFormattedTime(timeInMillis: Long): String {
            val dateFormat = SimpleDateFormat("mm:ss", Locale.US)
            val dateTime = Date(timeInMillis)
            return dateFormat.format(dateTime)
        }


    }

    interface ClickItem {
        fun more(index: Int, binding: ItemRecordBinding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListRecord(list: MutableList<NTDOtherRecord>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordsViewHolder =
        RecordsViewHolder(
            ItemRecordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecordsViewHolder, position: Int) {
        val item = list[position] ?: return
        holder.onBind(item, position)
    }


}
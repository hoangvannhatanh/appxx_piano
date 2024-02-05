package com.ntd.appid1.appid2.appid3.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.databinding.ItemLanguageBinding
import com.ntd.appid1.appid2.appid3.extensions.setBackGroundDrawable
import com.ntd.appid1.appid2.appid3.model.NTDModelLanguage

class NTDOtherLanguageAdapter(
    private val context: Context,
    private val onItemClick: (NTDModelLanguage) -> Unit
) :
    RecyclerView.Adapter<NTDOtherLanguageAdapter.ViewHolder>() {
    private lateinit var binding: ItemLanguageBinding
    private val listData: ArrayList<NTDModelLanguage> = arrayListOf()
    private var currentPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemLanguageBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listData[position], position)
    }

    inner class ViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(languageModel: NTDModelLanguage, position: Int) {
            binding.imgCountry.setImageDrawable(languageModel.image?.let {
                ContextCompat.getDrawable(context, it)
            })
            binding.txtLanguage.text = languageModel.languageName
            if (currentPosition == position) {
                binding.root.setBackGroundDrawable(R.drawable.radio_flat_selected)
            } else {
                binding.root.setBackGroundDrawable(R.drawable.radio_flat_unselected)
            }
            binding.root.setOnClickListener {
                onItemClick(languageModel)
                setCurrentPosition(position)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listAddress: ArrayList<NTDModelLanguage>) {
        listData.clear()
        listData.addAll(listAddress)
        notifyDataSetChanged()
    }

    fun setCurrentPosition(position: Int) {
        notifyItemChanged(currentPosition)
        currentPosition = position
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int = listData.size
}
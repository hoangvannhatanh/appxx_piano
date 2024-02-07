package com.ntd.appid1.appid2.appid3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ntd.appid1.appid2.appid3.R
import com.ntd.appid1.appid2.appid3.model.NTDModelIntro

class NTDOtherHowtoAdapter(context: Context) : RecyclerView.Adapter<NTDOtherHowtoAdapter.PageHolder>() {
    private lateinit var listPage: MutableList<NTDModelIntro>
    private lateinit var context: Context
    private var w = context.resources.displayMetrics.widthPixels / 100f


    fun newInstance(context: Context) {
        this.context = context
    }

    fun setData(listPage: MutableList<NTDModelIntro>) {
        this.listPage = listPage
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_howto, parent, false)
        return PageHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (listPage.isNotEmpty()) listPage.size else 0
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.onBind(position)
    }

    inner class PageHolder(private val view: View) : ViewHolder(view) {
        val ivItem = view.findViewById<AppCompatImageView>(R.id.ivItem)

        init {
        }

        fun onBind(position: Int) {
            val item = listPage[position]
            if (item.imgPage != -1) ivItem.setImageResource(item.imgPage)
        }
    }

}
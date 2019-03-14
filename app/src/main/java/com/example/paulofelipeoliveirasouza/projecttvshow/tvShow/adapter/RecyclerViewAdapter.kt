package com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.paulofelipeoliveirasouza.projecttvshow.R
import com.example.paulofelipeoliveirasouza.projecttvshow.data.Shows
import com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.adapter.viewHolder.CardViewHolder

class RecyclerViewAdapter(val arrayList: ArrayList<Shows>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CardViewHolder(inflater.inflate(R.layout.card_tv_show, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shows = arrayList[position]

        if(holder is CardViewHolder){
            holder.bind(shows.show)
        }
    }

    override fun getItemCount(): Int = arrayList.size

    fun setData(it: ArrayList<Shows>){
        arrayList.addAll(it)
        notifyDataSetChanged()
    }
}
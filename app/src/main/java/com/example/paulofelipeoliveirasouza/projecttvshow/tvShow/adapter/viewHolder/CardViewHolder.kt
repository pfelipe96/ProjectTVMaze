package com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.adapter.viewHolder

import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.paulofelipeoliveirasouza.projecttvshow.R
import com.example.paulofelipeoliveirasouza.projecttvshow.data.Show
import com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.view.DetailTvShow
import kotlin.text.StringBuilder

class CardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    val imageView = itemView.findViewById<ImageView>(R.id.image_show_tv)
    val titleView = itemView.findViewById<TextView>(R.id.title)
    val genresView = itemView.findViewById<TextView>(R.id.list_genres)
    val cardView = itemView.findViewById<CardView>(R.id.card_view_show_tv)

    fun bind(objectValue: Show?){
        Glide.with(imageView.context)
                .load(objectValue?.image?.medium)
                .into(imageView)

        titleView.text = objectValue?.title
        genresView.text = createAppendListForGenres(objectValue?.genres)

        cardView.setOnClickListener{
            val intent = Intent(it.context, DetailTvShow::class.java)

            intent.putExtra("title", objectValue?.title)
            intent.putExtra("date", objectValue?.date)
            intent.putExtra("genres_list", objectValue?.genres)
            intent.putExtra("description", objectValue?.summary)
            intent.putExtra("image", objectValue?.image?.original)
        }
    }

    private fun createAppendListForGenres(arrayList: ArrayList<String>?) : String{
        val append = StringBuilder()

        arrayList?.forEachIndexed {index, it ->
            if(index != arrayList.size - 1)
                append.append(" $it * ")
            else
                append.append(" $it")
        }

        return append.toString()
    }
}
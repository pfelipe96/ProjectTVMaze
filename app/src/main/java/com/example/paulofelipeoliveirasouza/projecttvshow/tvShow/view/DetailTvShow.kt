package com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.paulofelipeoliveirasouza.projecttvshow.R
import kotlinx.android.synthetic.main.detail_card_tv_show.*
import java.util.*

class DetailTvShow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_card_tv_show)
        val title = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val genresList = intent.getStringExtra("genres_list")
        val description = intent.getStringExtra("description")
        val image = intent.getStringExtra("image")

        setupToolBar(title)

        Glide.with(image_detail_tv_show.context)
                .load(image)
                .into(image_detail_tv_show)

        title_detail_tv_show.text = title
        description_detail_tv_show.text = description
        date_detail_tv_show.text = Date(date).toGMTString()
        list_genres_detail_tv_show.text = genresList
    }

    private fun setupToolBar(it: String) {
        setSupportActionBar(toolbar_detail)
        supportActionBar?.apply {
            title = it
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
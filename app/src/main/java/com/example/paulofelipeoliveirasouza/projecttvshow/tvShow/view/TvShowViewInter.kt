package com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.view

import com.example.paulofelipeoliveirasouza.projecttvshow.data.Shows

interface TvShowViewInter{
    fun showMessage(it: String)
    fun loadData(it: ArrayList<Shows>)
}
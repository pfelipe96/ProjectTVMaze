package com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.presenter

import com.example.paulofelipeoliveirasouza.projecttvshow.data.Shows
import com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.view.TvShowViewInter

interface TvShowPresenterInter{
    fun initializeModel()
    fun showMessage(it: String)
    fun getInstancePresenterInterface(interfaceValue: TvShowViewInter)
    fun getApiTvSMaze(it: String)
    fun returnData(it: ArrayList<Shows>)
}
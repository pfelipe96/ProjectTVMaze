package com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.model

import com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.presenter.TvShowPresenterInter

interface TvShowModelInter{
    fun getInstancePresenterInterface(interfaceValue : TvShowPresenterInter)
    fun getApiTvSMaze(it: String)
}
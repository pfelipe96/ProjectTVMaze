package com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.presenter

import com.example.paulofelipeoliveirasouza.projecttvshow.data.Shows
import com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.model.TvShowModel
import com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.view.TvShowViewInter
import java.lang.ref.WeakReference

class TvShowPresenter : TvShowPresenterInter {

    private var reference: WeakReference<TvShowViewInter>? = null

    private val tvShowViewInter: TvShowViewInter?
        get() = reference?.get()

    companion object {
        @JvmStatic
        lateinit var sInstance: TvShowPresenter

        @JvmStatic
        @Synchronized
        fun initializeInstance() {
            if (!::sInstance.isInitialized) {
                sInstance = TvShowPresenter()
            }
        }

        @JvmStatic
        @Synchronized
        fun getInstance(): TvShowPresenter {
            if (!::sInstance.isInitialized) {
                throw IllegalStateException(TvShowPresenter::class.java.simpleName + " is not initialized, call initializeInstance(..) method first.")
            }
            return sInstance
        }
    }


    override fun getApiTvSMaze(it: String) {
        TvShowModel.getInstance().getApiTvSMaze(it)
    }

    override fun getInstancePresenterInterface(interfaceValue: TvShowViewInter) {
        reference = WeakReference(interfaceValue)
    }

    override fun initializeModel() {
        TvShowModel.initializeInstance()
        TvShowModel.getInstance().getInstancePresenterInterface(this)
    }

    override fun showMessage(it: String) {
        tvShowViewInter?.showMessage(it)
    }

    override fun returnData(it: ArrayList<Shows>) {
        tvShowViewInter?.loadData(it)
    }

}
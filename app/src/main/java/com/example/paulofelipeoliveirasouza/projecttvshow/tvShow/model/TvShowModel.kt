package com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.model

import com.example.paulofelipeoliveirasouza.projecttvshow.data.Shows
import com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.presenter.TvShowPresenterInter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.NullPointerException
import java.lang.ref.WeakReference

class TvShowModel : TvShowModelInter{

    private var reference: WeakReference<TvShowPresenterInter>? = null


    private val tvShowPresenterInter: TvShowPresenterInter?
        get() = reference?.get()


    companion object {
        @JvmStatic
        lateinit var sInstance: TvShowModel

        @JvmStatic
        @Synchronized
        fun initializeInstance() {
            if (!::sInstance.isInitialized) {
                sInstance = TvShowModel()
            }
        }

        @JvmStatic
        @Synchronized
        fun getInstance(): TvShowModel {
            if (!::sInstance.isInitialized) {
                throw IllegalStateException(TvShowModel::class.java.simpleName + " is not initialized, call initializeInstance(..) method first.")
            }
            return sInstance
        }
    }

    override fun getInstancePresenterInterface(interfaceValue: TvShowPresenterInter) {
        reference = WeakReference(interfaceValue)
    }

    override fun getApiTvSMaze(it: String) {
        val url = "http://api.tvmaze.com/search/shows?q=$it"

        val client = OkHttpClient.Builder().build()

        val request = Request.Builder()
                .url(url)
                .get()
                .build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                try {
                    tvShowPresenterInter?.showMessage(e.message!!)
                }catch (e: NullPointerException){
                    e.printStackTrace()
                }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val jsonData = response.body()?.string()

                val shows = Gson().fromJson<ArrayList<Shows>>(jsonData, object : TypeToken<ArrayList<Shows>>() {}.type)

                if (response.isSuccessful) {
                    if(shows.isNotEmpty())
                        tvShowPresenterInter?.returnData(shows)
                    else
                        tvShowPresenterInter?.showMessage("Pesquisa não encontrada")
                } else {
                    tvShowPresenterInter?.showMessage(it)
                }
            }
        })
    }
}
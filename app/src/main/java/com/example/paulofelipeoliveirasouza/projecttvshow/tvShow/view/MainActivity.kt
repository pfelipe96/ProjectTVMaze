package com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.example.paulofelipeoliveirasouza.projecttvshow.R
import com.example.paulofelipeoliveirasouza.projecttvshow.data.Shows
import com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.adapter.RecyclerViewAdapter
import com.example.paulofelipeoliveirasouza.projecttvshow.tvShow.presenter.TvShowPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TvShowViewInter, SearchView.OnQueryTextListener {

    private val arrayListShow = ArrayList<Shows>()
    private var recyclerViewAdapter = RecyclerViewAdapter(arrayListShow)
    private var search: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeInstances()
        initializeToolBar()
    }

    private fun initializeToolBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search, menu)

        val activeSearch = intent?.extras?.getBoolean("active_search", false)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search = menu?.findItem(R.id.search)?.actionView as SearchView
        search?.queryHint = Html.fromHtml("<font color = #ffffff>" + "Pesquisar" + "</font>")
        search?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        search?.setOnQueryTextListener(this)

        try {
            if (activeSearch!!) {
                search?.isFocusable = false
                search?.isIconified = false
                search?.requestFocusFromTouch()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            TvShowPresenter.getInstance().getApiTvSMaze(it)
        }

        return true
    }

    private fun initializeInstances() {
        TvShowPresenter.initializeInstance()
        TvShowPresenter.getInstance().initializeModel()
        TvShowPresenter.getInstance().getInstancePresenterInterface(this)
        YourAppGlideModule()
    }

    @GlideModule
    inner class YourAppGlideModule : AppGlideModule() {
        override fun applyOptions(context: Context, builder: GlideBuilder) {
            val diskCacheSizeBytes = (1024 * 1024 * 150).toLong() // 100 MB
            builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes))
        }
    }

    override fun loadData(it: ArrayList<Shows>) {
        runOnUiThread {
            if (arrayListShow.isNotEmpty()) {
                arrayListShow.clear()
            }

            val linearLayoutManager = LinearLayoutManager(this)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

            recycler_view?.apply {
                this.visibility = View.VISIBLE
                this.layoutManager = linearLayoutManager
                this.setHasFixedSize(true)
                this.adapter = recyclerViewAdapter
            }

            recyclerViewAdapter.setData(it)
        }
    }

    override fun showMessage(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
    }
}
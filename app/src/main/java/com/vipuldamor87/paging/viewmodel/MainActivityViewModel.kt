package com.vipuldamor87.paging.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vipuldamor87.paging.model.RecyclerListDataSourceFactory
import com.vipuldamor87.paging.data.RecyclerData
import java.util.concurrent.Executors

class MainActivityViewModel: ViewModel() {
    private var recyclerList: LiveData<PagedList<RecyclerData>>? = null

    init {
        initpaging()
    }

    private fun initpaging() {
        val factory = RecyclerListDataSourceFactory()
        val config = PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(2).build()

        val executor= Executors.newFixedThreadPool(5)
        recyclerList = LivePagedListBuilder<Int,RecyclerData>(factory,config)
            .setFetchExecutor(executor)
            .build()
    }

    fun getRecyclerListObserver(): LiveData<PagedList<RecyclerData>>? {

        return recyclerList
    }
}
package com.vipuldamor87.paging.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.vipuldamor87.paging.data.RecyclerData
import com.vipuldamor87.paging.model.RecyclerListDataSource


class RecyclerListDataSourceFactory(): DataSource.Factory<Int,RecyclerData>()  {
    private var mutableLiveData: MutableLiveData<RecyclerListDataSource>? = null
    init{
        mutableLiveData = MutableLiveData()
    }
    override fun create(): DataSource<Int, RecyclerData> {
        val listDataSource = RecyclerListDataSource()
        mutableLiveData?.postValue(listDataSource)
        return  listDataSource
    }
}

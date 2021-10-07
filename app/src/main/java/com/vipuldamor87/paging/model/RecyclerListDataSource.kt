package com.vipuldamor87.paging.model

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.vipuldamor87.paging.data.RecyclerData
import com.vipuldamor87.paging.data.RecyclerList
import com.vipuldamor87.paging.network.RetroInstance
import com.vipuldamor87.paging.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerListDataSource: PageKeyedDataSource<Int,RecyclerData>() {
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RecyclerData>) {
        val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(params.key,10)
        call.enqueue(object:Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {

                if(response.isSuccessful){
                    callback.onResult(response?.body()?.data!!,params.key+1)}
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                Log.d("MYtag","notworking")
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RecyclerData>) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RecyclerData>
    ) {
           val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)
            val call = retroInstance.getDataFromApi(1,10)
        call.enqueue(object:Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {

                if(response.isSuccessful){
               callback.onResult(response?.body()?.data!!,null,1)}
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                Log.d("MYtag","notworking")
            }

        })
    }
}
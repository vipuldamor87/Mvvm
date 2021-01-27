package com.vipuldamor87.mvvm3.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vipuldamor87.mvvm3.adapter.RecyclerViewAdapter
import com.vipuldamor87.mvvm3.data.RecyclerData
import com.vipuldamor87.mvvm3.data.RecyclerList
import com.vipuldamor87.mvvm3.network.RetroInstance
import com.vipuldamor87.mvvm3.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainVM :ViewModel() {
    lateinit var recyclerListData: MutableLiveData<RecyclerList>
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    init{
        recyclerListData = MutableLiveData()
        recyclerViewAdapter = RecyclerViewAdapter()
    }
    fun getAdapter():RecyclerViewAdapter{
        return recyclerViewAdapter
    }
    fun setAdapterData(data: ArrayList<RecyclerData>){
        recyclerViewAdapter.setDataList(data)
        recyclerViewAdapter.notifyDataSetChanged()
    }
    fun getRecyclerListDataObserver(): MutableLiveData<RecyclerList>{
        return  recyclerListData
    }
    fun makeApicall(input : String){
       val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(input)
        call.enqueue(object : Callback<RecyclerList>{
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
              recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if(response.isSuccessful){
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }
        })
    }

    fun postApiCall(){
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        //val call = retroInstance.createUser(name,job)
    }

}
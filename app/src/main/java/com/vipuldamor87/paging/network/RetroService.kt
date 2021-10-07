package com.vipuldamor87.paging.network


import com.vipuldamor87.paging.data.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("passenger")
    fun getDataFromApi(@Query("page")page : Int,
                        @Query("size")size : Int) : Call<RecyclerList>
}
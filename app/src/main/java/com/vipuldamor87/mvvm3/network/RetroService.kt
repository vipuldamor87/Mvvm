package com.vipuldamor87.mvvm3.network

import com.vipuldamor87.mvvm3.data.RecyclerList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroService {

    @GET("users?page=2")
    fun getDataFromApi(@Query("q")query : String) : Call<RecyclerList>

    @POST("users")
    fun createUser(@Field("name")name: String,
                    @Field("job")job: String):Call<ResponseBody>
}
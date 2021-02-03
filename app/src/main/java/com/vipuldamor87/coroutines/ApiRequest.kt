package com.vipuldamor87.coroutines

import com.vipuldamor87.coroutines.api.CatJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {

    @GET("/facts/random")
    fun getcatfacts(): Call<CatJson>
}
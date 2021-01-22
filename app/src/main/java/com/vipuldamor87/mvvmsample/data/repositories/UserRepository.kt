package com.vipuldamor87.mvvmsample.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vipuldamor87.mvvmsample.data.network.MyApi
import com.vipuldamor87.mvvmsample.data.network.SafeApiRequest
import com.vipuldamor87.mvvmsample.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class UserRepository : SafeApiRequest() {
    suspend fun userLogin(email: String,password: String): AuthResponse{
        return apiRequest { MyApi().userLogin(email, password) }
    }
}
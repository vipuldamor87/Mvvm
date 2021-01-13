package com.vipuldamor87.mvvmsample.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    fun onStarted()
    fun onSucces(loginResponse: LiveData<String>)
    fun onFailure(message: String)
}
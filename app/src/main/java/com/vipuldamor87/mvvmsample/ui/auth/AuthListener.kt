package com.vipuldamor87.mvvmsample.ui.auth

import androidx.lifecycle.LiveData
import com.vipuldamor87.mvvmsample.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSucces(user: User)
    fun onFailure(message: String)
}
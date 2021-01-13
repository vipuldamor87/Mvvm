package com.vipuldamor87.mvvmsample.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.vipuldamor87.mvvmsample.data.repositories.UserRepository

class AuthViewModel : ViewModel() {

    var email : String? = null
    var password : String? = null
    var authListener : AuthListener? = null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() ||password.isNullOrEmpty()){
            authListener?.onFailure("invalid Credentials")
            return
        }

        val loginResponse = UserRepository().userLogin(email!!,password!!)
        authListener?.onSucces(loginResponse)
        //success

    }

}
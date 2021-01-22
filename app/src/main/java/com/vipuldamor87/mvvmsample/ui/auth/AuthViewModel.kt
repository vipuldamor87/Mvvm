package com.vipuldamor87.mvvmsample.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.vipuldamor87.mvvmsample.data.repositories.UserRepository
import com.vipuldamor87.mvvmsample.util.Coroutines
import java.lang.Exception

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

        Coroutines.main{
            try {
                val authResponse = UserRepository().userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSucces(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: Exception){
                authListener?.onFailure(e.message!!)
            }


        }

        //success

    }

}
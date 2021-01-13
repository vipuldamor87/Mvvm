package com.vipuldamor87.mvvmsample.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vipuldamor87.mvvmsample.R
import com.vipuldamor87.mvvmsample.databinding.ActivityLoginBinding
import com.vipuldamor87.mvvmsample.util.hide
import com.vipuldamor87.mvvmsample.util.show
import com.vipuldamor87.mvvmsample.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSucces(loginResponse: LiveData<String>) {
       // progress_bar.hide()
       loginResponse.observe(this, Observer {
           toast(it)
       })
    }

    override fun onFailure(message: String) {
        //progress_bar.hide()
        toast(message)
    }
}
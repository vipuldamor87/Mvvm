package com.vipuldamor87.mvvm_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vipuldamor87.mvvm_3.databinding.ActivityMainBinding
import com.vipuldamor87.mvvm_3.db.Subscriber
import com.vipuldamor87.mvvm_3.db.SubscriberDao
import com.vipuldamor87.mvvm_3.db.SubscriberDatabase
import com.vipuldamor87.mvvm_3.Repository.SubscriberRepository
import com.vipuldamor87.mvvm_3.ui.SubscriberViewModel
import com.vipuldamor87.mvvm_3.ui.SubscriberViewModelFactory
import com.vipuldamor87.mvvmsample.util.toast

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var  subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao : SubscriberDao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
       initRecyclerView()

        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                toast(it)
            }
        })

    }
    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            binding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(it
            ) { selectedItem: Subscriber -> listItemClicked(selectedItem) }
        })

    }

    private fun listItemClicked(subscriber: Subscriber){
        toast("selected name is ${subscriber.name}")
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}
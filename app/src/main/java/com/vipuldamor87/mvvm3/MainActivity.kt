 package com.vipuldamor87.mvvm3

import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.vipuldamor87.mvvm3.data.RecyclerList
import com.vipuldamor87.mvvm3.databinding.ActivityMainBinding
import com.vipuldamor87.mvvm3.model.MainVM
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = makeApicall()
        setupBinding(viewModel)
    }
     fun setupBinding(viewModel :MainVM){
         val activityMainBinding: ActivityMainBinding =DataBindingUtil.setContentView(this,R.layout.activity_main)
         activityMainBinding.setVariable(BR.viewModel,viewModel)
         activityMainBinding.executePendingBindings()
         recycler1.apply {
             layoutManager = LinearLayoutManager(this@MainActivity)
             val decoration = DividerItemDecoration(this@MainActivity,VERTICAL)
             addItemDecoration(decoration)
         }
     }

    fun makeApicall(): MainVM {
        val viewModel = ViewModelProviders.of(this).get(MainVM::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList> {
           pb1.visibility = GONE
            if(it != null){

                viewModel.setAdapterData(it.data)
            }else{
                Toast.makeText(this@MainActivity,"Error in fetching data",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApicall("users?page=2")
        return viewModel
    }
}
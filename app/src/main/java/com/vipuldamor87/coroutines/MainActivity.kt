package com.vipuldamor87.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.measureTimeMillis


const val BASE_URL = "https://cat-fact.herokuapp.com"
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout1.setOnClickListener {  getCurrentData() }


        GlobalScope.launch(Dispatchers.IO) {
           val answer = doNetworkCall()
            withContext(Dispatchers.Main) {
                tv1.text = answer.toString()
                delay(2000L)
                tv1.text = "this is after Delay"
            }
        }

        runBlocking {
            launch(Dispatchers.IO)
            {
                delay(2000L)
                Log.d("mytag","Finished to coroutine 1")
            }
            launch(Dispatchers.IO)
            {
              delay(1000L)
                Log.d("mytag","finished Coroutine 2")

            }

            Log.d("mytag","start of runblocking")
            delay(5000L)
            Log.d("mytag", "end of runblocking")
        }
        GlobalScope.launch(Dispatchers.IO){
            val time = measureTimeMillis {
                val answer1 = networkcall1()
                val answer2 = networkcall2()
                Log.d("MyTag","$answer2")
                Log.d("tag","$answer1")
            }

        }


        Log.d("mytag","hello from main thread ${Thread.currentThread().name}")
    }

    private fun getCurrentData() {
        tv_textView.visibility = View.INVISIBLE
        tv_timestamp.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val respone = api.getcatfacts().awaitResponse()
            if (respone.isSuccessful){
                val data = respone.body()
                Log.d("mytag","sucess")

                withContext(Dispatchers.Main){
                    tv_textView.visibility = View.VISIBLE
                    tv_timestamp.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE

                    tv_textView.text = data!!.text
                    tv_timestamp.text = data!!.createdAt
                }
            }

        }
    }

    suspend fun  doNetworkCall():String {
        delay(3000L)
        //Log.d("mytag","coroutines says hello from thread ${Thread.currentThread().name}")
        return "thsi is the answer"
    }

    suspend fun  networkcall2() : String{
        delay(3000L)
        return "answer 2"
    }
    suspend fun  networkcall1() : String{
        delay(3000L)
        return "answer1"
    }
}
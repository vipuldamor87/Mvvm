package com.vipuldamor87.mvvm_3.Repository

import com.vipuldamor87.mvvm_3.db.Subscriber
import com.vipuldamor87.mvvm_3.db.SubscriberDao

class SubscriberRepository(private  val dao : SubscriberDao) {

    val subscribers = dao.getAllSubscribers()
    suspend fun insert(subscriber: Subscriber){
        dao.insertSubscriber(subscriber)
    }
    suspend fun  update(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }
    suspend fun  delete(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }
    suspend fun  deleteAll(){
        dao.deleteAll()
    }
}

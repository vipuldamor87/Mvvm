package com.vipuldamor87.mvvm_3.ui

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipuldamor87.mvvm_3.db.Subscriber
import com.vipuldamor87.mvvm_3.Repository.SubscriberRepository
import com.vipuldamor87.mvvm_3.util.Event
import kotlinx.coroutines.launch

class SubscriberViewModel(private  val repository: SubscriberRepository) : ViewModel(),Observable {

    val subscribers = repository.subscribers
    private var isUpdateorDelete = false
    private lateinit var  subscriberToUpdateOrDelete : Subscriber

    @Bindable
    val inputName = MutableLiveData<String>()
    @Bindable
    val inputEmail = MutableLiveData<String>()
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessage

    init{
        saveOrUpdateButtonText.value = "save"
        clearAllOrDeleteButtonText.value = "clear all"
    }

    fun saveOrUpdate(){
        if(isUpdateorDelete){
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.email = inputEmail.value!!
            update(subscriberToUpdateOrDelete)
        }
        val name= inputName.value!!
        val email = inputEmail.value!!
        insert(Subscriber(0,name,email))
        inputName.value = null
        inputEmail.value = null
    }
    fun clearAllOrDelete(){
        if(isUpdateorDelete){
            delete(subscriberToUpdateOrDelete)
        }else{
            clearAll()
        }
    }
    fun insert(subscriber: Subscriber) = viewModelScope.launch {
            repository.insert(subscriber)
            statusMessage.value = Event("inserted successfully")
        }

    fun update(subscriber: Subscriber) = viewModelScope.launch {
        repository.update(subscriber)
        inputName.value = null
        inputName.value = null
        isUpdateorDelete = false
        saveOrUpdateButtonText.value = "save"
        clearAllOrDeleteButtonText.value = "clear all"
        statusMessage.value= Event("updated successfully")
    }

    fun delete(subscriber: Subscriber) = viewModelScope.launch {
        repository.delete(subscriber)
        inputName.value = null
        inputName.value = null
        isUpdateorDelete = false
        saveOrUpdateButtonText.value = "save"
        clearAllOrDeleteButtonText.value = "clear all"
        statusMessage.value= Event("selected deleted successfully")
    }

    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
        statusMessage.value= Event("all deleted successfully")
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputName.value = subscriber.email
        isUpdateorDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
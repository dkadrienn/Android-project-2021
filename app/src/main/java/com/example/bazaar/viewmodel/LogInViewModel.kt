package com.example.bazaar.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.MyApplication
import com.example.bazaar.model.Login
import com.example.bazaar.model.LoginRequest
import com.example.bazaar.repository.MarketRepository

class LogInViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var token: MutableLiveData<String> = MutableLiveData()
    var login = MutableLiveData<Login>()

    init {
        login.value = Login()
    }

    suspend fun login() {
        val request =
            LoginRequest(username = login.value!!.username, password = login.value!!.password)
        try {
            val result = repository.login(request)
            MyApplication.token = result.token
            token.value = result.token
            Log.d(TAG, "MyApplication - token:  ${MyApplication.token}")
        } catch (e: Exception) {
            Log.d(TAG, "LoginViewModel - exception: ${e.toString()}")
        }
    }
}
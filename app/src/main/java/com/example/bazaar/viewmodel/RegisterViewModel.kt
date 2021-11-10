package com.example.bazaar.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.Register
import com.example.bazaar.model.RegisterRequest
import com.example.bazaar.repository.MarketRepository

class RegisterViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var register = MutableLiveData<Register>()

    init {
        register.value = Register()
    }

    suspend fun register() {
        val request =
            RegisterRequest(
                username = register.value!!.username,
                password = register.value!!.password,
                email = register.value!!.email,
                phone_number = register.value!!.phone_number,
                firebase_token = register.value!!.firebase_token,
                userImage = register.value!!.userImage!!
            )
        try {
            val result = repository.register(request)
        } catch (e: Exception) {
            Log.d(TAG, "LoginViewModel - exception: ${e.toString()}")
        }
    }
}
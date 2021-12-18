package com.example.bazaar.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
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
        try {
            val request =
                RegisterRequest(
                    username = register.value!!.username,
                    password = register.value!!.password,
                    email = register.value!!.email,
                    phone_number = register.value!!.phone_number,
                    firebase_token = register.value!!.firebase_token,
                    userImage = register.value!!.userImage!!
                )
            val result = repository.register(request)
        } catch (e: Exception) {
            Log.d(TAG, "RegisterViewModel - exception: ${e.toString()}")
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
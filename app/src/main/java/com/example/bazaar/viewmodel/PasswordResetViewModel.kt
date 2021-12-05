package com.example.bazaar.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.PasswordReset
import com.example.bazaar.model.PasswordResetRequest
import com.example.bazaar.repository.MarketRepository

class PasswordResetViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var passwordReset = MutableLiveData<PasswordReset>()

    init {
        passwordReset.value = PasswordReset()
    }

    suspend fun passwordReset() {
        val request =
            PasswordResetRequest(
                username = passwordReset.value!!.username,
                email = passwordReset.value!!.email
            )
        try {
            val result = repository.password_reset(request)
        } catch (e: Exception) {
            Log.d(TAG, "LoginViewModel - exception: ${e.toString()}")
        }
    }
}
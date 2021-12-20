package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.Login
import com.example.bazaar.model.LoginRequest
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants

class LogInViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var token: MutableLiveData<String> = MutableLiveData()
    var login = MutableLiveData<Login>()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )

    init {
        login.value = Login()
    }

    suspend fun login() {
        val request =
            LoginRequest(username = login.value!!.username, password = login.value!!.password)
        try {
            val result = repository.login(request)
            val edit = sharedPreferences.edit()
            edit.putString(Constants.sharedPrefKeyToken, result.token)
            edit.putString(Constants.sharedPrefKeyEmail, result.email)
            edit.putString(Constants.sharedPrefKeyPhoneNr, result.phone_number.toString())
            edit.apply()
            edit.commit()
            token.value = result.token
            Log.d(TAG, "MyToken - token:  ${result.token}")
        } catch (e: Exception) {
            Log.d(TAG, "LoginViewModel - exception: ${e.toString()}")
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
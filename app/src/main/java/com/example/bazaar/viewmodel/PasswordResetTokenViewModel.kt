package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.ResetPasswordToken
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants

class PasswordResetTokenViewModel(val context: Context, val repository: MarketRepository) :
    ViewModel() {
    val TAG = Class::class.java.simpleName
    var newPwd = MutableLiveData<ResetPasswordToken>()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val token = sharedPreferences.getString(Constants.sharedPrefKeyToken, "token")

    init {
        newPwd.value = ResetPasswordToken()
    }

    suspend fun resetPasswordWithToken() {
        val request = ResetPasswordToken(
            new_password = newPwd.value!!.new_password,
        )
        try {
            val result = repository.resetPasswordWithToken(
                token!!,
                request.new_password
            )
            Toast.makeText(context, "Successfully edited", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Log.d(TAG, "ResetPwdViewModel - exception: ${e.toString()}")
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
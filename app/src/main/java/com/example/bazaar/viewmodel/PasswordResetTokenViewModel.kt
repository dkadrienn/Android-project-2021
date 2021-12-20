package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.ResetPasswordTokenResponse
import com.example.bazaar.model.UpdateUserDataRequest
import com.example.bazaar.model.UpdatedData
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants

class PasswordResetTokenViewModel(val context: Context, val repository: MarketRepository) :
    ViewModel() {
    val TAG = Class::class.java.simpleName
    var message = MutableLiveData<ResetPasswordTokenResponse>()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val token = sharedPreferences.getString(Constants.sharedPrefKeyToken, "token")

    init {
        message.value = ResetPasswordTokenResponse()
    }

    suspend fun resetPasswordWithToken() {
//        val request = Reset(
//            username = updated.value!!.username,
//            email = updated.value!!.email,
//            phone_number = updated.value!!.phone_number
//        )
//        try {
//            val result = repository.resetPasswordWithToken(
//                token!!,
//                request.username
//            )
//            val edit = sharedPreferences.edit()
//            edit.putString(Constants.sharedPrefKeyUsername, result.updatedData.username)
//            edit.putString(Constants.sharedPrefKeyEmail, result.updatedData.email)
//            edit.putString(
//                Constants.sharedPrefKeyPhoneNr,
//                result.updatedData.phone_number.toString()
//            )
//            edit.apply()
//            edit.commit()
//            Toast.makeText(context, "Successfully edited", Toast.LENGTH_LONG).show()
//        } catch (e: Exception) {
//            Log.d(TAG, "ResetPwdViewModel - exception: ${e.toString()}")
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
//        }
    }
}
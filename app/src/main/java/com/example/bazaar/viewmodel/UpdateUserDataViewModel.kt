package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.UpdateUserDataRequest
import com.example.bazaar.model.UpdatedData
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants

class UpdateUserDataViewModel(val context: Context, val repository: MarketRepository) :
    ViewModel() {
    val TAG = Class::class.java.simpleName
    var updated = MutableLiveData<UpdatedData>()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val token = sharedPreferences.getString(Constants.sharedPrefKeyToken, "token")

    init {
        updated.value = UpdatedData()
    }

    suspend fun updateUserData() {
        Log.d(TAG, "New data " + updated.value.toString())
        val request = UpdateUserDataRequest(
            username = updated.value!!.username,
            email = updated.value!!.email,
            phone_number = updated.value!!.phone_number
        )
        try {
            val result = repository.updateUserData(
                token!!,
                request.username,
                request.email,
                request.phone_number
            )
            val edit = sharedPreferences.edit()
            edit.putString(Constants.sharedPrefKeyUsername, result.updatedData.username)
            edit.putString(Constants.sharedPrefKeyEmail, result.updatedData.email)
            edit.putString(
                Constants.sharedPrefKeyPhoneNr,
                result.updatedData.phone_number.toString()
            )
            edit.apply()
            edit.commit()
            Toast.makeText(context, "Successfully edited", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Log.d(TAG, "UserUpdateViewModel - exception: ${e.toString()}")
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
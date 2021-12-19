package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.model.OtherUser
import com.example.bazaar.model.Product
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants
import kotlinx.coroutines.launch

class OtherUserViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var user: MutableLiveData<OtherUser> = MutableLiveData()

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val username = sharedPreferences.getString(Constants.sharedPrefKeyUser, "user")

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getUser(username!!)
                Log.d(TAG, "Result " + username + "/"+ result.toString())
                if(result.data.isNotEmpty()){
                    user.value = result.data[0]
                    Log.d(TAG, "OtherUserViewMode - #products:  ${user.value!!.username}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "OtherUserViewMode exception: ${e.toString()}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
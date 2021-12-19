package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.model.Order
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants
import kotlinx.coroutines.launch

class OrderViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var orders: MutableLiveData<List<Order>> = MutableLiveData()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val token = sharedPreferences.getString(Constants.sharedPrefKeyToken, "token")

    init {
        getOrders()
    }

    private fun getOrders() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getOrders(token.toString(), 100)
                orders.value = result.orders
                Log.d(TAG, "OrderViewModel - #products:  ${result.item_count}")
            } catch (e: Exception) {
                Log.d(TAG, "OrderViewModel exception: ${e.toString()}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
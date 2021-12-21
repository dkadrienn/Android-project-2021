package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.AddOrder
import com.example.bazaar.model.AddOrderRequest
import com.example.bazaar.model.AddProduct
import com.example.bazaar.model.AddProductRequest
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants

class AddOrderViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var newOrder = MutableLiveData<AddOrder>()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val token = sharedPreferences.getString(Constants.sharedPrefKeyToken, "token")

    init {
        newOrder.value = AddOrder()
    }

    suspend fun addOrder() {
        Log.d(TAG, "New product " + newOrder.value.toString())
        val request = AddOrderRequest(
            title = newOrder.value!!.title,
            description = newOrder.value!!.description,
            price_per_unit = newOrder.value!!.price_per_unit,
            units = newOrder.value!!.units,
            owner_username = newOrder.value!!.owner_username
        )
        try {
            repository.addOrder(
                token!!,
                request.title,
                request.description,
                request.price_per_unit,
                request.units,
                request.owner_username
            )
            Toast.makeText(context, "Successfully added a new order", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Log.d(TAG, "AddOrderViewModel - exception: ${e.toString()}")
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
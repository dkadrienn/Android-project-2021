package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.UpdateProduct
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants

class UpdateProductViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var updated = MutableLiveData<UpdateProduct>()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val token = sharedPreferences.getString(Constants.sharedPrefKeyToken, "token")

    init {
        updated.value = UpdateProduct()
    }

    suspend fun updateProduct() {
        Log.d(TAG, "New data " + updated.value.toString())
        val request = UpdateProduct(
            product_id = updated.value!!.product_id,
            price_per_unit = updated.value!!.price_per_unit,
            is_active = updated.value!!.is_active,
            title = updated.value!!.title,
            rating = updated.value!!.rating,
            amount_type = updated.value!!.amount_type,
            price_type = updated.value!!.price_type
        )
        Log.d(TAG, "New data " + request.toString())
        try {
            val result = repository.updateProduct(
                token!!,
                request.product_id,
                request.price_per_unit,
                request.is_active,
                request.title,
                request.rating,
                request.amount_type,
                request.price_type
            )
            Log.d(TAG, "Result data " + request.toString())
            Toast.makeText(context, "Successfully edited", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Log.d(TAG, "ProductUpdateViewModel - exception: ${e.toString()}")
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
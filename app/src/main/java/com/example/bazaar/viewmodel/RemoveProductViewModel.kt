package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.RemoveProduct
import com.example.bazaar.model.RemoveProductResponse
import com.example.bazaar.model.UpdateProduct
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants

class RemoveProductViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var removed = MutableLiveData<RemoveProductResponse>()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val token = sharedPreferences.getString(Constants.sharedPrefKeyToken, "token")

    init {
        removed.value = RemoveProductResponse()
    }

    suspend fun removeProduct() {
        val request = RemoveProduct(
            product_id = removed.value!!.product_id
        )
        try {
            val result = repository.removeProduct(
                token!!,
                request.product_id
            )
            Toast.makeText(context, "Successfully removed", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Log.d(TAG, "RemoveViewModel - exception: ${e.toString()}")
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
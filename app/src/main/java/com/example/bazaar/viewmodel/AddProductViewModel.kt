package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.model.AddProduct
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants
import okhttp3.MediaType
import okhttp3.RequestBody


class AddProductViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var newProduct = MutableLiveData<AddProduct>()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val token = sharedPreferences.getString(Constants.sharedPrefKeyToken, "token")

    init {
        newProduct.value = AddProduct()
    }

    suspend fun addProduct() {
        Log.d(TAG, "alma " + newProduct.value.toString())
//        val request =
//            AddProductRequest(
//                title = newProduct.value!!.title,
//                description = newProduct.value!!.description,
//                price_per_unit = newProduct.value!!.price_per_unit,
//                unit = newProduct.value!!.unit,
//                is_active = newProduct.value!!.is_active,
//                rating = newProduct.value!!.rating,
//                amount_type = newProduct.value!!.amount_type,
//                price_type = newProduct.value!!.price_type
//            )
        try {
            repository.addProduct(
                token!!,
                newProduct.value!!.title,
                newProduct.value!!.description,
                newProduct.value!!.price_per_unit,
                newProduct.value!!.unit,
                newProduct.value!!.is_active,
                newProduct.value!!.rating,
                newProduct.value!!.amount_type,
                newProduct.value!!.price_type
            )
            Toast.makeText(context, "Successfully added a new product", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Log.d(TAG, "AddProductViewModel - exception: ${e.toString()}")
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
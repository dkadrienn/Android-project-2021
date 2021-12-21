package com.example.bazaar.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.model.Product
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants
import kotlinx.coroutines.launch

class ProductListViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_FILE,
        Context.MODE_PRIVATE
    )
    val token = sharedPreferences.getString(Constants.sharedPrefKeyToken, "token")

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getProducts(token.toString(), 500)
                products.value = result.products
                Log.d(TAG, "ListViewModel - #products:  ${result.item_count}")
            } catch (e: Exception) {
                Log.d(TAG, "ListViewModel exception: ${e.toString()}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
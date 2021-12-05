package com.example.bazaar.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MyApplication
import com.example.bazaar.model.Product
import com.example.bazaar.repository.MarketRepository
import kotlinx.coroutines.launch

class ProductListViewModel(val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName
    var products: MutableLiveData<List<Product>> = MutableLiveData()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getProducts(MyApplication.token)
                products.value = result.products
                Log.d(TAG, "ListViewModel - #products:  ${result.item_count}")
            } catch (e: Exception) {
                Log.d(TAG, "ListViewModel exception: ${e.toString()}")
            }
        }
    }
}
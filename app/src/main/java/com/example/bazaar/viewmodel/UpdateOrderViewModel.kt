package com.example.bazaar.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.bazaar.repository.MarketRepository

class UpdateOrderViewModel(val context: Context, val repository: MarketRepository) : ViewModel() {
    val TAG = Class::class.java.simpleName

}
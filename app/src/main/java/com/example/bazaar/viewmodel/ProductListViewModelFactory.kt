package com.example.bazaar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar.repository.MarketRepository

class ProductListViewModelFactory(private val repository: MarketRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(repository) as T
    }
}
package com.example.bazaar.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar.repository.MarketRepository

class RemoveProductViewModelFactory(
    private val context: Context,
    private val repository: MarketRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RemoveProductViewModel(context, repository) as T
    }
}
package com.example.bazaar.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar.repository.MarketRepository

class UpdateProfileViewModelFactory(
    private val context: Context,
    private val repository: MarketRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpdateProfileViewModel(context, repository) as T
    }
}
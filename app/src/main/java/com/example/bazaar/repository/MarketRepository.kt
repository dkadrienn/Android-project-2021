package com.example.bazaar.repository

import com.example.bazaar.api.RetrofitInstance
import com.example.bazaar.model.*

class MarketRepository {
    suspend fun register(request: RegisterRequest): RegisterResponse {
        return RetrofitInstance.api.register(request)
    }

    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun passwordReset(request: PasswordResetRequest): PasswordResetResponse {
        return RetrofitInstance.api.passwordReset(request)
    }

    suspend fun getProducts(token: String, limit: Int): ProductResponse {
        return RetrofitInstance.api.getProducts(token, limit)
    }

    suspend fun addProducts(
        token: String,
        title: String,
        description: String,
        price_per_unit: String,
        unit: String,
        is_active: Boolean,
        rating: Double,
        amount_type: String,
        price_type: String
        ): AddProductResponse {
        return RetrofitInstance.api.addProducts(
            token,
            title,
            description,
            price_per_unit,
            unit,
            is_active,
            rating,
            amount_type,
            price_type
        )
    }

}
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

//    suspend fun addProduct(token: String, request: AddProductRequest): AddProductResponse{
//        return RetrofitInstance.api.addProduct(token, request)
//    }

    suspend fun addProduct(
        token: String,
        title: String,
        description: String,
        price_per_unit: String,
        units: String,
        is_active: Boolean,
        amount_type: String,
        price_type: String,
        rating: Double
        ): AddProductResponse {
        return RetrofitInstance.api.addProduct(
            token,
            title,
            description,
            price_per_unit,
            units,
            is_active,
            amount_type,
            price_type,
            rating)
    }

    suspend fun getUser(username: String): OtherUserResponse {
        return RetrofitInstance.api.getUser(username)
    }

    suspend fun getOrders(token: String, limit: Int): OrderResponse {
        return RetrofitInstance.api.getOrders(token, limit)
    }
}
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

    suspend fun updateUserData(
        token: String,
        username: String,
        email: String,
        phone_number: Int
    ): UpdateUserDataResponse {
        return RetrofitInstance.api.updateUserData(
            token,
            username,
            email,
            phone_number)
    }

    suspend fun resetPasswordWithToken(token: String, new_password:String): ResetPasswordTokenResponse {
        return RetrofitInstance.api.resetPasswordWithToken(token, new_password)
    }

    suspend fun addOrder(
        token: String,
        title: String,
        description: String,
        price_per_unit: String,
        units: String,
        owner_username: String
    ): AddOrderResponse {
        return RetrofitInstance.api.addOrder(
            token,
            title,
            description,
            price_per_unit,
            units,
            owner_username)
    }
}
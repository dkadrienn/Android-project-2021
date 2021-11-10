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

    suspend fun password_reset(request: PasswordResetRequest): PasswordResetResponse {
        return RetrofitInstance.api.password_reset(request)
    }

//    suspend fun getProducts(token: String): ProductResponse {
//        return RetrofitInstance.api.getProducts(token)
//    }

}
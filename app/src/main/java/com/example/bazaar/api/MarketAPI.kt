package com.example.bazaar.api

import com.example.bazaar.model.*
import com.example.bazaar.utils.Constants
import retrofit2.http.Body
import retrofit2.http.POST

interface MarketAPI {
    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST(Constants.PASSWORD_RESET_URL)
    suspend fun password_reset(@Body reques: PasswordResetRequest): PasswordResetResponse

//    @GET(Constants.GET_PRODUCT_URL)
//    suspend fun getProducts(@Header("token") token: String): ProductResponse
}
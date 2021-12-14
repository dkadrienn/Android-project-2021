package com.example.bazaar.api

import com.example.bazaar.model.*
import com.example.bazaar.utils.Constants
import retrofit2.http.*

interface MarketAPI {
    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST(Constants.PASSWORD_RESET_URL)
    suspend fun password_reset(@Body reques: PasswordResetRequest): PasswordResetResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse

    @Multipart
    @POST(Constants.Add_PRODUCT)
    suspend fun addProducts(
        @Header("token") token: String,
        @Part("title") title: String,
        @Part("description") description: String,
        @Part("price_per_unit") price_per_unit: String,
        @Part("unit") unit: String,
        @Part("is_active") is_active: Boolean,
        @Part("rating") rating: Double,
        @Part("amount_type") amount_type: String,
        @Part("price_type") price_type: String,
    ): AddProductResponse
}
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
    suspend fun passwordReset(@Body request: PasswordResetRequest): PasswordResetResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String, @Header("limit") limit: Int): ProductResponse

    @Multipart
    @POST(Constants.Add_PRODUCT)
    suspend fun addProduct(
        @Header("token") token: String,
        @Part("title") title: String,
        @Part("description") description: String,
        @Part("price_per_unit") price_per_unit: String,
        @Part("units") units: String,
        @Part("is_active") is_active: Boolean,
        @Part("amount_type") amount_type: String,
        @Part("price_type") price_type: String,
        @Part("rating") rating: Double
        ): AddProductResponse

    @GET(Constants.GET_USER_DATA)
    suspend fun getUser(@Header("username") username: String) : OtherUserResponse

    @GET(Constants.GET_ORDERS_URL)
    suspend fun getOrders(@Header("token") token: String, @Header("limit") limit: Int): OrderResponse

    @POST(Constants.REMOVE_PRODUCT_URL)
    suspend fun removeProduct(@Header("token") token: String, @Path("product_id") product_id: String) : RemoveProductResponse

    @Multipart
    @POST(Constants.USER_UPDATE_URL)
    suspend fun updateUserData(
        @Header("token") token: String,
        @Part("username") username: String,
        @Part("email") email: String,
        @Part("phone_number") phone_number: Int
    ): UpdateUserDataResponse

    @GET(Constants.PASSWORD_RESET_TOKEN_URL)
    suspend fun resetPasswordWithToken(@Header("token") token: String, @Header("new_password") new_password: String): ResetPasswordTokenResponse
}
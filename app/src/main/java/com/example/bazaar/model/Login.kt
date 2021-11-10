package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class Login(
    val username : String="",
    val password : String=""
)

data class LoginRequest (
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String
)

data class LoginResponse (
    @SerializedName("username")
    var username: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("phone_number")
    var phone_number: Int,

    @SerializedName("token")
    var token: String,

    @SerializedName("creation_time")
    var creation_time: Long,

    @SerializedName("refresh_time")
    var refresh_time: Long
)
package com.example.bazaar.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class Register(
    var username : String="",
    var password : String="",
    var email : String="",
    var phone_number : Int=0,
    val firebase_token : String="token",
    val userImage : File? = null
)

data class RegisterRequest (
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("phone_number")
    var phone_number: Int,

    @SerializedName("firebase_token")
    var firebase_token: String,

    @SerializedName("userImage")
    val userImage: File
)

data class RegisterResponse (
    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("creation_time")
    var creation_time: Long
)
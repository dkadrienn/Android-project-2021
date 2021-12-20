package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class ResetPasswordToken(
    var new_password: String = "1234"
)

data class ResetPasswordTokenResponse(
    @SerializedName("message")
    val message : String = ""
)

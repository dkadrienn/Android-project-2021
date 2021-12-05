package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class PasswordReset(
    var username: String = "",
    var email: String = ""
)

data class PasswordResetRequest(
    @SerializedName("username")
    var username: String,

    @SerializedName("email")
    var email: String
)

data class PasswordResetResponse(
    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("timestamp")
    var timestamp: Long
)
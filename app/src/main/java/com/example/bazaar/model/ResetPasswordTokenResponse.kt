package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class ResetPasswordTokenResponse(
    @SerializedName("message")
    val message : String = ""
)

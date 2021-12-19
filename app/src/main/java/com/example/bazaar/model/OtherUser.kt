package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class OtherUser(
    var username: String = "",
    var phone_number: Int = 0,
    var email: String = "",
    var firebase_token: String = "",
    var is_activated: Boolean = true,
    var creation_time: String = ""
)

data class OtherUserRequest(
    @SerializedName("username")
    var username: String = ""
)

data class OtherUserResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: List<OtherUser>,
    @SerializedName("timestamp")
    var timestamp: Long
)
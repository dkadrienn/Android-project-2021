package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class UpdatedData(
    var _id: String = "",
    var username: String = "",
    var password: String = "",
    var phone_number: Int = 0,
    var email: String = "",
    var is_activated: Boolean = true,
    var image_url: String = "",
    var image_id: String = "",
    var creation_time: String = "",
    var __v : Int = 0
)

data class UpdateUserDataRequest(
    @SerializedName("username")
    var username: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("phone_number")
    var phone_number: Int = 0
)

data class UpdateUserDataResponse(
    @SerializedName("updatedData")
    var updatedData: UpdatedData
)

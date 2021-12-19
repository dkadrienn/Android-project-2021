package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class UpdateProfile (
    var phone_number: String = "",
    var email: String = "",
    var username: String = "",
    var userImage: Image = Image("", "", "", "")
)

data class UpdateProfileRequest(
    @SerializedName("phone_number")
    var phone_number: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("userImage")
    var userImage: Image
)

data class UpdatedProfile(
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

data class UpdateProfileResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("updatedData")
    var updatedData: UpdatedProfile,
    @SerializedName("timestamp")
    var timestamp: Long
)
package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class RemoveProductResponse(
    @SerializedName("message")
    var message: String,
    @SerializedName("product_id")
    var product_id: String,
    @SerializedName("deletion_time")
    var deletion_time: Long
)

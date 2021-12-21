package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class AddOrder(
    var title: String = "",
    var description: String = "",
    var price_per_unit: String = "",
    var units: String = "",
    var owner_username: String = ""
)

data class AddOrderRequest(
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("price_per_unit")
    var price_per_unit: String = "",
    @SerializedName("units")
    var units: String = "",
    @SerializedName("owner_username")
    var owner_username: String = ""
)

data class AddOrderResponse(
    @SerializedName("creation")
    var creation: String = "",
    @SerializedName("order_id")
    var order_id: String = "",
    @SerializedName("username")
    var username: String = "",
    @SerializedName("status")
    var status: String = "",
    @SerializedName("owner_username")
    var owner_username: String = "",
    @SerializedName("price_per_unit")
    var price_per_unit: String = "",
    @SerializedName("units")
    var units: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("images")
    var images: List<Image> = listOf(),
    @SerializedName("creation_time")
    var creation_time: Long = 0
)

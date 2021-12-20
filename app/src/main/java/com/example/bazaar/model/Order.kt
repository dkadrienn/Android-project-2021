package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class Order(
    var order_id: String = "",
    var username: String = "",
    var status: String = "",
    var price_per_unit: String = "",
    var price_type: String = "",
    var units: String = "",
    var amount_type: String = "",
    var description: String = "",
    var title: String = "",
    var images: List<Image> = listOf(),
    var creation_time: Long = 0
)

data class OrderResponse(
    @SerializedName("item_count")
    val item_count: Int,
    @SerializedName("orders")
    val orders: List<Order>,
    @SerializedName("timestamp")
    val timestamp: Long
)
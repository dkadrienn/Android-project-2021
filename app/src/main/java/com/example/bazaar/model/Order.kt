package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class Order(
    val order_id: String = "",
    val username: String = "",
    val status: String = "",
    val price_per_unit: String = "",
    val price_type: String = "",
    val units: String = "",
    val amount_type: String = "",
    val description: String = "",
    val title: String = "",
    val images: List<Image> = listOf(),
    val creation_time: Long = 0
)

data class OrderResponse(
    @SerializedName("item_count")
    val item_count: Int,
    @SerializedName("orders")
    val orders: List<Order>,
    @SerializedName("timestamp")
    val timestamp: Long
)
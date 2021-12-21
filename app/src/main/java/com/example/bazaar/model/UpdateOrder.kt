package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class UpdateOrder(
    @SerializedName("price_per_unit")
    var price_per_unit: String = "",
    @SerializedName("status")
    var status: String = "",
    @SerializedName("title")
    var title: String = ""
)

data class UpdateOrderResponse(
    @SerializedName("order_id")
    var order_id: String = ""
)
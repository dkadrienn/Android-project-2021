package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class UpdateProduct(
    @SerializedName("product_id")
    var product_id: String = "",
    @SerializedName("price_per_unit")
    var price_per_unit: Double = 0.0,
    @SerializedName("is_active")
    var is_active: Boolean = true,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("rating")
    var rating: String = "",
    @SerializedName("amount_type")
    var amount_type: String = "",
    @SerializedName("price_type")
    var price_type: String = ""
)

data class UpdatedProduct(
    @SerializedName("rating")
    var rating: Double = 5.0,
    @SerializedName("amount_type")
    var amount_type: String = "",
    @SerializedName("price_type")
    var price_type: String = "",
    @SerializedName("product_id")
    var product_id: String = "",
    @SerializedName("username")
    var username: String = "",
    @SerializedName("is_active")
    var is_active: Boolean = true,
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
    var creation_time: Long = 0,
    @SerializedName("messages")
    var messages: List<String> = listOf()
)

data class UpdateProductResponse(
    @SerializedName("updated_item")
    var updated_item: UpdatedProduct
)
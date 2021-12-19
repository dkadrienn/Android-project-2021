package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class Image(
    val _id: String,
    val image_id: String,
    val image_name: String,
    val image_path: String
)

data class Product(
    val rating: Double = 0.0,
    val amount_type: String = "",
    val price_type: String = "",
    val product_id: String = "",
    val username: String = "",
    val is_active: Boolean = true,
    val price_per_unit: String = "",
    val units: String = "",
    val description: String = "",
    val title: String = "",
    val images: List<Image> = listOf(),
    val creation_time: Long = 0
)

data class ProductResponse(
    @SerializedName("item_count")
    val item_count: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("timestamp")
    val timestamp: Long
)
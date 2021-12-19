package com.example.bazaar.model

import com.google.gson.annotations.SerializedName

data class AddProduct(
    var title: String = "",
    var description: String = "",
    var price_per_unit: String = "",
    var units: String = "",
    var is_active: Boolean = true,
    var rating: Double = 0.0,
    var amount_type: String = "",
    var price_type: String = ""
)

data class AddProductRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price_per_unit")
    val price_per_unit: String,
    @SerializedName("units")
    val units: String,
    @SerializedName("is_active")
    val is_active: Boolean,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("amount_type")
    val amount_type: String,
    @SerializedName("price_type")
    val price_type: String
)

data class AddProductResponse(
    @SerializedName("creation")
    val creation: String,
    @SerializedName("product_id")
    val product_id: String,
    @SerializedName("username")
    val username: String,
//    @SerializedName("is_active")
//    val is_active: Boolean,
    @SerializedName("price_per_unit")
    val price_per_unit: String,
    @SerializedName("units")
    val units: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String,
//    @SerializedName("rating")
//    val rating: Double,
    @SerializedName("amount_type")
    val amount_type: String,
    @SerializedName("price_type")
    val price_type: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("creation_time")
    val creation_time: Long
)

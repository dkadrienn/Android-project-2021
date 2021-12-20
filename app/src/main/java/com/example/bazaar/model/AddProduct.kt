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
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("price_per_unit")
    var price_per_unit: String,
    @SerializedName("units")
    var units: String,
    @SerializedName("is_active")
    var is_active: Boolean,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("amount_type")
    var amount_type: String,
    @SerializedName("price_type")
    var price_type: String
)

data class AddProductResponse(
    @SerializedName("creation")
    var creation: String,
    @SerializedName("product_id")
    var product_id: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("is_active")
    var is_active: Boolean,
    @SerializedName("price_per_unit")
    var price_per_unit: String,
    @SerializedName("units")
    var units: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("amount_type")
    var amount_type: String,
    @SerializedName("price_type")
    var price_type: String,
    @SerializedName("images")
    var images: List<Image>,
    @SerializedName("creation_time")
    var creation_time: Long
)

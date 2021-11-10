package com.example.bazaar.api

import com.example.bazaar.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object RetrofitInstance {
    private val retrofit by lazy{
    Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    val api: MarketAPI by lazy{
        retrofit.create(MarketAPI :: class.java)
    }
}
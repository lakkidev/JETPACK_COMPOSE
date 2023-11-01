package com.ashok.myapplication.data.api

import com.ashok.myapplication.data.entity.Products
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun geAllProducts():Response<Products>
}
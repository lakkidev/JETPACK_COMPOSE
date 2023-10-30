package com.ashok.myapplication.api

import com.ashok.myapplication.models.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun geApi():Response<Products>
}
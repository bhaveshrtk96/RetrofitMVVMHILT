package com.example.fakeapiretrofit.network

import com.example.fakeapiretrofit.sharedmodelentities.ProductPojo
import com.example.fakeapiretrofit.sharedmodelentities.ProductPojoItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FakeApiQueries {
    @GET("products")
    suspend fun getAllProducts(): Response<ProductPojo>

    @POST("products")
    suspend fun postProduct(@Body productPojo: ProductPojoItem): Response<ProductPojoItem>
}
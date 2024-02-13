package com.example.fakeapiretrofit.datasource

import com.example.fakeapiretrofit.modelentities.ProductPojo
import com.example.fakeapiretrofit.modelentities.ProductPojoItem
import com.example.fakeapiretrofit.network.FakeApiQueries
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val fakeApiQueries: FakeApiQueries) {
    suspend fun getAllProducts():
            Response<ProductPojo> {
        return fakeApiQueries.getAllProducts()
    }

    suspend fun postProduct(productPojo: ProductPojoItem): Response<ProductPojoItem> {
        return fakeApiQueries.postProduct(productPojo)
    }
}
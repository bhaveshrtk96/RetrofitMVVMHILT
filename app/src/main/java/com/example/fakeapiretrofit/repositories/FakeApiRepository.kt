package com.example.fakeapiretrofit.repositories

import com.example.fakeapiretrofit.datasource.LocalDataSource
import com.example.fakeapiretrofit.datasource.RemoteDataSource
import com.example.fakeapiretrofit.modelentities.ProductPojo
import com.example.fakeapiretrofit.modelentities.ProductPojoItem
import retrofit2.Response
import javax.inject.Inject

class FakeApiRepository @Inject constructor(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
) {
    suspend fun getAllProducts():
            Response<ProductPojo> {
        return remoteDataSource.getAllProducts()
    }

    suspend fun postProduct(productPojo: ProductPojoItem): Response<ProductPojoItem> {
        return remoteDataSource.postProduct(productPojo)
    }
}
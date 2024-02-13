package com.example.fakeapiretrofit.repositories

import com.example.fakeapiretrofit.datasource.LocalDataSource
import com.example.fakeapiretrofit.datasource.RemoteDataSource
import com.example.fakeapiretrofit.sharedmodelentities.ProductPojo
import com.example.fakeapiretrofit.sharedmodelentities.ProductPojoItem
import retrofit2.Response
import javax.inject.Inject

class ProductPojoRepository @Inject constructor(
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

    suspend fun insertProductPojoItem(productPojoItem: ProductPojoItem){
        localDataSource.insertProductPojoItem(productPojoItem)
    }

    suspend fun getAllProjectItems(): MutableList<ProductPojoItem>{
        return localDataSource.getAllProjectItems()
    }
}
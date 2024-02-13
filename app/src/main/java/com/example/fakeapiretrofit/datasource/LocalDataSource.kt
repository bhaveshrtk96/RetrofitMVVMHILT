package com.example.fakeapiretrofit.datasource

import com.example.fakeapiretrofit.database.ProductPojoItemDao
import com.example.fakeapiretrofit.sharedmodelentities.ProductPojoItem
import javax.inject.Inject

/**
 * This Class can be user for room
 */
class LocalDataSource @Inject constructor(val dao: ProductPojoItemDao) {
    suspend fun insertProductPojoItem(productPojoItem: ProductPojoItem){
        dao.insertProductPojoItem(productPojoItem)
    }

    suspend fun getAllProjectItems(): MutableList<ProductPojoItem>{
        return dao.getAllProjectItems()
    }
}
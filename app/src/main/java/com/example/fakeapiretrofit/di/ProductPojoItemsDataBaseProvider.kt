package com.example.fakeapiretrofit.di

import android.content.Context
import androidx.room.Room
import com.example.fakeapiretrofit.database.ProductPojoItemDao
import com.example.fakeapiretrofit.database.ProductPojoItemsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ProductPojoItemsDataBaseProvider {
    @Singleton
    @Provides
    fun getProductPojoItemsDataBase(@ApplicationContext context: Context): ProductPojoItemsDataBase {
        return Room.databaseBuilder(
            context,
            ProductPojoItemsDataBase::class.java,
            "ProductPojoItemsDataBase"
        ).build()
    }

    @Singleton
    @Provides
    fun getProductPojoItemsDao(productPojoItemsDataBase: ProductPojoItemsDataBase): ProductPojoItemDao {
        return productPojoItemsDataBase.getProductPojoItemDao()
    }
}
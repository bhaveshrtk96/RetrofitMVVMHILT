package com.example.fakeapiretrofit.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fakeapiretrofit.sharedmodelentities.ProductPojoItem

@Database(entities = [ProductPojoItem::class], version = 1, exportSchema = true)
abstract class ProductPojoItemsDataBase : RoomDatabase(){
    abstract fun getProductPojoItemDao(): ProductPojoItemDao
}
package com.example.fakeapiretrofit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fakeapiretrofit.sharedmodelentities.ProductPojoItem

@Dao
interface ProductPojoItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductPojoItem(productPojoItem: ProductPojoItem)

    @Query("SELECT * FROM ProductPojoItems ORDER BY id ASC")
    suspend fun getAllProjectItems(): MutableList<ProductPojoItem>
}
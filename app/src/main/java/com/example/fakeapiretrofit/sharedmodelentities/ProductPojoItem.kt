package com.example.fakeapiretrofit.sharedmodelentities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductPojoItems")
data class ProductPojoItem(
    val category: String = "",
    val description: String = "",
    @PrimaryKey
    val id: Int = 1,
    val image: String = "",
    val price: Double = 500.0,
    @Embedded
    val rating: Rating = Rating(5, 5.0),
    val title: String = ""
)
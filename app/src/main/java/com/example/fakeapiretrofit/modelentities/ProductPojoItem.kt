package com.example.fakeapiretrofit.modelentities

data class ProductPojoItem(
    val category: String = "",
    val description: String = "",
    val id: Int = 1,
    val image: String = "",
    val price: Double = 500.0,
    val rating: Rating = Rating(5, 5.0),
    val title: String = ""
)
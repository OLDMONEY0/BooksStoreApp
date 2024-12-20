package com.example.booksstoreapp.model.api

import com.example.booksstoreapp.model.db.Book
import com.squareup.moshi.Json

data class BooksResponse(
    @Json(name = "items")
    val books: List<Book>,
    @Json(name = "totalItems")
    val totalItems: Int
)

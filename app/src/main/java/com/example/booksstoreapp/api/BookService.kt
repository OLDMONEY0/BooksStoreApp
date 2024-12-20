package com.example.booksstoreapp.api

import com.example.booksstoreapp.model.api.BooksResponse
import com.example.booksstoreapp.model.db.Book
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    suspend fun getBooks(): BooksResponse

    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): BooksResponse

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): Book
} 
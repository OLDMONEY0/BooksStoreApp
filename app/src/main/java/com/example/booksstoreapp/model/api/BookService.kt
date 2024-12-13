package com.example.booksstoreapp.model.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BookService {
    @GET("//books/v1/volumes?maxResults=40")
    fun searchBooks(@Query("startIndex") startIndex: Int,
                    @Query("q") searchString: String,
                    @Query("key") apiKey: String
    ): Single<BooksResponse>

    @GET("volumes/{id}")
    fun getBook(
        @Path("id") id: String
    ): Single<BooksResponse>
}
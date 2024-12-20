package com.example.booksstoreapp.util

import android.content.Context
import com.example.booksstoreapp.api.BookService
import com.example.booksstoreapp.model.db.BookDao
import com.example.booksstoreapp.model.db.BookDatabase
import com.example.booksstoreapp.repository.BookRepository
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Injection {

    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    private fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    private fun provideBookService(retrofit: Retrofit): BookService {
        return retrofit.create(BookService::class.java)
    }

    private fun provideDatabase(context: Context): BookDatabase {
        return BookDatabase.getInstance(context)
    }

    private fun provideBookDao(database: BookDatabase): BookDao {
        return database.bookDao()
    }

    fun provideBookRepository(context: Context): BookRepository {
        val moshi = provideMoshi()
        val okHttpClient = provideOkHttpClient()
        val retrofit = provideRetrofit(moshi, okHttpClient)
        val bookService = provideBookService(retrofit)
        val database = provideDatabase(context)
        val bookDao = provideBookDao(database)
        return BookRepository(bookService, bookDao)
    }
} 
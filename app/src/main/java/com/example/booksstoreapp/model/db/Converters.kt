package com.example.booksstoreapp.model.db

import androidx.room.TypeConverter
import com.example.booksstoreapp.model.api.ImageLinks
import com.example.booksstoreapp.model.api.VolumeInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    private val moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, String::class.java)
    private val listAdapter = moshi.adapter<List<String>>(listType)
    private val imageLinksAdapter = moshi.adapter(ImageLinks::class.java)

    @TypeConverter
    fun fromAuthorsList(authors: List<String>?): String? {
        return authors?.let { listAdapter.toJson(it) }
    }

    @TypeConverter
    fun toAuthorsList(authorsJson: String?): List<String>? {
        return authorsJson?.let { listAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromImageLinks(imageLinks: ImageLinks?): String? {
        return imageLinks?.let { imageLinksAdapter.toJson(it) }
    }

    @TypeConverter
    fun toImageLinks(imageLinksJson: String?): ImageLinks? {
        return imageLinksJson?.let { imageLinksAdapter.fromJson(it) }
    }
} 
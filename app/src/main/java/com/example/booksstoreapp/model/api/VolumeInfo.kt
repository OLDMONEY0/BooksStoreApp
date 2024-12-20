package com.example.booksstoreapp.model.api

import androidx.room.TypeConverters
import com.example.booksstoreapp.model.db.Converters

data class VolumeInfo(
    val title: String,
    val subtitle: String?,
    @TypeConverters(Converters::class)
    val authors: List<String>?,
    val description: String?,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String?
)

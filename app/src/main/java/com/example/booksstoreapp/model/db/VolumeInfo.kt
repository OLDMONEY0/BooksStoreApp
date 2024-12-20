package com.example.booksstoreapp.model.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    val id: Int,
    val selfLink: String,
    @Embedded
    val volumeInfo: VolumeInfo,
    var isFavorite: Boolean = false,
    var isWishlist: Boolean = false
)

data class VolumeInfo(
    val title: String,
    val description: String
)

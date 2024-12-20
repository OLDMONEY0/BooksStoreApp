package com.example.booksstoreapp.model.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.booksstoreapp.model.api.VolumeInfo

@Entity(
    tableName = "books",
    indices = [Index("selfLink")]
)
data class Book(
    @PrimaryKey
    val id: Int,
    val selfLink: String,

    @Embedded(prefix = "volumeInfo_")
    val volumeInfo: VolumeInfo,

    var isFavorite: Boolean = false,
    var isWishlist: Boolean = false
) {
    companion object {
        const val UNKNOWN_ID = -1
    }
}
package com.example.booksstoreapp.model.db

import androidx.room.Entity
import androidx.room.Index


@Entity(
    indices = [
        Index("id"),
        Index("selfLink"),
        Index("volumeInfo")
    ],
    primaryKeys = ["id"]
)

data class Book(
    val id: Int,
    val selfLink: String,
    val volumeInfo: VolumeInfo
) {

    companion object {
        const val UNKNOWN_ID = -1
    }
}
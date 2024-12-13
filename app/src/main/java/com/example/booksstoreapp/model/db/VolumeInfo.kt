package com.example.booksstoreapp.model.db

import androidx.room.Entity

@Entity(primaryKeys = ["title"])
data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val description: String
)

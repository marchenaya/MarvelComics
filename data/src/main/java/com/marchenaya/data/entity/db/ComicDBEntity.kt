package com.marchenaya.data.entity.db

import androidx.room.Entity

@Entity(tableName = "comic")
data class ComicDBEntity(
    val id: Int,
    val title: String,
    val description: String,
    val pageCount: Int,
    val image: String
)

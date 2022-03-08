package com.marchenaya.data.entity.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic")
data class ComicDBEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val pageCount: Int,
    val image: String,
    val favorite: Boolean
)

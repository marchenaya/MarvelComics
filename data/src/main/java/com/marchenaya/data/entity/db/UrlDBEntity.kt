package com.marchenaya.data.entity.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "url")
data class UrlDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val comicId: Int?,
    val url: String
)

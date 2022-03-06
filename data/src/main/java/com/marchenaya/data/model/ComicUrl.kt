package com.marchenaya.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic_url")
data class ComicUrl(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val comicId: Int,
    val url: String
)

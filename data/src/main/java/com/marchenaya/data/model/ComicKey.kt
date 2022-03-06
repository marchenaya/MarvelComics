package com.marchenaya.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic_key")
data class ComicKey(
    @PrimaryKey
    val comicId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)

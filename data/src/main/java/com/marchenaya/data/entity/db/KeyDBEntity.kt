package com.marchenaya.data.entity.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "key")
data class KeyDBEntity(
    @PrimaryKey
    val comicId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)

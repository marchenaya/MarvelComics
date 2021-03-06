package com.marchenaya.data.entity.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "creator")
data class CreatorDBEntity(
    @PrimaryKey val id: Int,
    val comicId: Int?,
    val name: String,
    val image: String
)

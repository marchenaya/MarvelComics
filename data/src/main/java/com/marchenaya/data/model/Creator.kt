package com.marchenaya.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "creator")
data class Creator(
    @PrimaryKey val id: Int,
    val comicId: Int? = 0,
    val name: String,
    val image: String
)

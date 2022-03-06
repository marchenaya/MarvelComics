package com.marchenaya.data.entity.db

import androidx.room.Entity

@Entity(tableName = "url")
data class UrlDBEntity(val comicId: Int, val url: String)

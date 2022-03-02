package com.marchenaya.data.entity.remote

import com.google.gson.annotations.SerializedName

data class ComicRemoteEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("thumbnail") val image: ImageRemoteEntity,
    @SerializedName("urls") val urls: List<UrlRemoteEntity>
)
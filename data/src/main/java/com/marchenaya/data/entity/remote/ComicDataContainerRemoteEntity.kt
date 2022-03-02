package com.marchenaya.data.entity.remote

import com.google.gson.annotations.SerializedName

data class ComicDataContainerRemoteEntity(
    @SerializedName("results") val results: List<ComicRemoteEntity>
)

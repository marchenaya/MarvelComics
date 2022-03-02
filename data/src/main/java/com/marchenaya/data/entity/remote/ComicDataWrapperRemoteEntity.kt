package com.marchenaya.data.entity.remote

import com.google.gson.annotations.SerializedName

data class ComicDataWrapperRemoteEntity(
    @SerializedName("data") val data: ComicDataContainerRemoteEntity
)

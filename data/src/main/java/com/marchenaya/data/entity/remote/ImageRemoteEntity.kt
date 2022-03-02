package com.marchenaya.data.entity.remote

import com.google.gson.annotations.SerializedName

data class ImageRemoteEntity(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)
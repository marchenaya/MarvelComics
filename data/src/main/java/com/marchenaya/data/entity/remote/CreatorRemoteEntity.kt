package com.marchenaya.data.entity.remote

import com.google.gson.annotations.SerializedName

data class CreatorRemoteEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("fullName") val name: String,
    @SerializedName("thumbnail") val image: ImageRemoteEntity
)
package com.marchenaya.data.entity.remote

import com.google.gson.annotations.SerializedName

data class CharacterRemoteEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("thumbnail") val image: ImageRemoteEntity
)
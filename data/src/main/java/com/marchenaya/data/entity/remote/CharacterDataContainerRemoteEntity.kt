package com.marchenaya.data.entity.remote

import com.google.gson.annotations.SerializedName

data class CharacterDataContainerRemoteEntity(
    @SerializedName("results") val results: List<CharacterRemoteEntity>
)

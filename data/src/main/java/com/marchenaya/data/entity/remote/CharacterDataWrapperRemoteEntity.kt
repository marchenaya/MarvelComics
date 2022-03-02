package com.marchenaya.data.entity.remote

import com.google.gson.annotations.SerializedName

data class CharacterDataWrapperRemoteEntity(
    @SerializedName("data") val data: CharacterDataContainerRemoteEntity
)

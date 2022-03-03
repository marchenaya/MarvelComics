package com.marchenaya.data.entity.remote

import com.google.gson.annotations.SerializedName

data class CreatorDataWrapperRemoteEntity(
    @SerializedName("data") val data: CreatorDataContainerRemoteEntity
)

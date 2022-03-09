package com.marchenaya.data.extensions

import com.marchenaya.data.entity.remote.ImageRemoteEntity

const val IMAGE_VARIANT = "portrait_uncanny"

fun String.convertToImageRemoteEntity(): ImageRemoteEntity {
    val path = this.substringBeforeLast("/")
    val extension = this.substringAfterLast(".")
    return ImageRemoteEntity(path, extension)
}
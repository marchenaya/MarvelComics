package com.marchenaya.data.mapper.remote

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.local.ComicEntity
import com.marchenaya.data.entity.remote.ComicRemoteEntity
import com.marchenaya.data.entity.remote.UrlRemoteEntity
import com.marchenaya.data.extensions.IMAGE_VARIANT
import com.marchenaya.data.extensions.convertToImageRemoteEntity
import com.marchenaya.data.mapper.base.RemoteMapper
import javax.inject.Inject

class ComicRemoteEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : RemoteMapper<ComicRemoteEntity, ComicEntity>() {

    override fun transformEntityToRemote(input: ComicEntity): ComicRemoteEntity {
        return ComicRemoteEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            pageCount = input.pageCount,
            image = input.image.convertToImageRemoteEntity(),
            urls = input.urls.map { UrlRemoteEntity(it) }
        )
    }

    override fun transformRemoteToEntity(input: ComicRemoteEntity): ComicEntity {
        return ComicEntity(
            id = input.id,
            title = input.title,
            description = input.description ?: "",
            pageCount = input.pageCount,
            image = "${
                input.image.path.replace(
                    "http",
                    "https"
                )
            }/$IMAGE_VARIANT.${input.image.extension}",
            urls = input.urls.map { it.url },
            characters = emptyList(),
            creators = emptyList(),
            favorite = false
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.REMOTE_MAPPER_COMIC, "error", error)
    }

}
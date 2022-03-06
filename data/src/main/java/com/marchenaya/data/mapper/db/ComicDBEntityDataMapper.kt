package com.marchenaya.data.mapper.db

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.db.ComicDBEntity
import com.marchenaya.data.entity.local.ComicEntity
import com.marchenaya.data.mapper.base.DBMapper
import javax.inject.Inject

class ComicDBEntityDataMapper @Inject constructor(private val traceComponent: TraceComponent) :
    DBMapper<ComicDBEntity, ComicEntity>() {

    override fun transformDBToEntity(input: ComicDBEntity): ComicEntity {
        return ComicEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            pageCount = input.pageCount,
            image = input.image,
            urls = emptyList(),
            characters = emptyList(),
            creators = emptyList()
        )
    }

    override fun transformEntityToDB(input: ComicEntity): ComicDBEntity {
        return ComicDBEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            pageCount = input.pageCount,
            image = input.image
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.DB_MAPPER_COMIC, "error", error)
    }

}
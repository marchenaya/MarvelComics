package com.marchenaya.data.mapper.db

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.db.UrlDBEntity
import com.marchenaya.data.entity.local.UrlEntity
import com.marchenaya.data.mapper.base.DBMapper
import javax.inject.Inject

class UrlDBEntityDataMapper @Inject constructor(private val traceComponent: TraceComponent) :
    DBMapper<UrlDBEntity, UrlEntity>() {

    override fun transformDBToEntity(input: UrlDBEntity): UrlEntity {
        return UrlEntity(
            input.comicId,
            input.url
        )
    }

    override fun transformEntityToDB(input: UrlEntity): UrlDBEntity {
        return UrlDBEntity(
            input.comicId,
            input.url
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.DB_MAPPER_URL, "error", error)
    }

}
package com.marchenaya.data.mapper.db

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.db.CreatorDBEntity
import com.marchenaya.data.entity.local.CreatorEntity
import com.marchenaya.data.mapper.base.DBMapper
import javax.inject.Inject

class CreatorDBEntityDataMapper @Inject constructor(private val traceComponent: TraceComponent) :
    DBMapper<CreatorDBEntity, CreatorEntity>() {

    override fun transformDBToEntity(input: CreatorDBEntity): CreatorEntity {
        return CreatorEntity(
            input.id,
            input.comicId,
            input.name,
            input.image
        )
    }

    override fun transformEntityToDB(input: CreatorEntity): CreatorDBEntity {
        return CreatorDBEntity(
            input.id,
            input.comicId,
            input.name,
            input.image
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.DB_MAPPER_CREATOR, "error", error)
    }

}
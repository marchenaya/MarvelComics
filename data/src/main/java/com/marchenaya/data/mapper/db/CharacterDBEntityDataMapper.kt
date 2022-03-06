package com.marchenaya.data.mapper.db

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.db.CharacterDBEntity
import com.marchenaya.data.entity.local.CharacterEntity
import com.marchenaya.data.mapper.base.DBMapper
import javax.inject.Inject

class CharacterDBEntityDataMapper @Inject constructor(private val traceComponent: TraceComponent) :
    DBMapper<CharacterDBEntity, CharacterEntity>() {

    override fun transformDBToEntity(input: CharacterDBEntity): CharacterEntity {
        return CharacterEntity(
            input.id,
            input.comicId,
            input.name,
            input.image
        )
    }

    override fun transformEntityToDB(input: CharacterEntity): CharacterDBEntity {
        return CharacterDBEntity(
            input.id,
            input.comicId,
            input.name,
            input.image
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.DB_MAPPER_CHARACTER, "error", error)
    }

}
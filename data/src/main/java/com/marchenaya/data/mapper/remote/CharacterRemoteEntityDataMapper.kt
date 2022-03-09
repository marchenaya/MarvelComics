package com.marchenaya.data.mapper.remote

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.local.CharacterEntity
import com.marchenaya.data.entity.remote.CharacterRemoteEntity
import com.marchenaya.data.extensions.IMAGE_VARIANT
import com.marchenaya.data.extensions.convertToImageRemoteEntity
import com.marchenaya.data.mapper.base.RemoteMapper
import javax.inject.Inject

class CharacterRemoteEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : RemoteMapper<CharacterRemoteEntity, CharacterEntity>() {

    override fun transformEntityToRemote(input: CharacterEntity): CharacterRemoteEntity {
        return CharacterRemoteEntity(input.id, input.name, input.image.convertToImageRemoteEntity())
    }

    override fun transformRemoteToEntity(input: CharacterRemoteEntity): CharacterEntity {
        return CharacterEntity(
            input.id,
            input.name,
            "${
                input.image.path.replace(
                    "http",
                    "https"
                )
            }/$IMAGE_VARIANT.${input.image.extension}"
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.REMOTE_MAPPER_CHARACTER, "error", error)
    }

}
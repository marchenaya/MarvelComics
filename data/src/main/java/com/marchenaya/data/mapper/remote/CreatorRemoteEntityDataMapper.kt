package com.marchenaya.data.mapper.remote

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.local.CreatorEntity
import com.marchenaya.data.entity.remote.CreatorRemoteEntity
import com.marchenaya.data.extension.IMAGE_VARIANT
import com.marchenaya.data.extension.convertToImageRemoteEntity
import com.marchenaya.data.mapper.base.RemoteMapper
import javax.inject.Inject

class CreatorRemoteEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : RemoteMapper<CreatorRemoteEntity, CreatorEntity>() {

    override fun transformEntityToRemote(input: CreatorEntity): CreatorRemoteEntity {
        return CreatorRemoteEntity(input.id, input.name, input.image.convertToImageRemoteEntity())
    }

    override fun transformRemoteToEntity(input: CreatorRemoteEntity): CreatorEntity {
        return CreatorEntity(
            input.id,
            input.name,
            "${input.image.path}/$IMAGE_VARIANT.${input.image.extension}"
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.REMOTE_MAPPER_CREATOR, "error", error)
    }

}
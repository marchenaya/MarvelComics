package com.marchenaya.data.mapper.local

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.local.CreatorEntity
import com.marchenaya.data.mapper.base.ModelMapper
import com.marchenaya.data.model.Creator
import javax.inject.Inject

class CreatorEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : ModelMapper<Creator, CreatorEntity>() {

    override fun transformEntityToModel(input: CreatorEntity): Creator {
        return Creator(input.id, input.name, input.image)
    }

    override fun transformModelToEntity(input: Creator): CreatorEntity {
        return CreatorEntity(
            input.id,
            input.name,
            input.image
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.MODEL_MAPPER_CREATOR, "error", error)
    }

}
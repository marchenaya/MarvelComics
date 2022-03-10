package com.marchenaya.data.mapper.local

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.local.CharacterEntity
import com.marchenaya.data.mapper.base.ModelMapper
import com.marchenaya.domain.model.Character
import javax.inject.Inject

class CharacterEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : ModelMapper<Character, CharacterEntity>() {

    override fun transformEntityToModel(input: CharacterEntity): Character {
        return Character(input.id, input.name, input.image)
    }

    override fun transformModelToEntity(input: Character): CharacterEntity {
        return CharacterEntity(
            input.id,
            input.name,
            input.image
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.MODEL_MAPPER_CHARACTER, "error", error)
    }

}
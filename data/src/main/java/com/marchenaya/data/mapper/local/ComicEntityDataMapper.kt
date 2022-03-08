package com.marchenaya.data.mapper.local

import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceId
import com.marchenaya.data.entity.local.ComicEntity
import com.marchenaya.data.mapper.base.ModelMapper
import com.marchenaya.data.model.Comic
import javax.inject.Inject

class ComicEntityDataMapper @Inject constructor(
    private val characterEntityDataMapper: CharacterEntityDataMapper,
    private val creatorEntityDataMapper: CreatorEntityDataMapper,
    private val traceComponent: TraceComponent
) : ModelMapper<Comic, ComicEntity>() {

    override fun transformEntityToModel(input: ComicEntity): Comic {
        return Comic(
            id = input.id,
            title = input.title,
            description = input.description,
            pageCount = input.pageCount,
            image = input.image,
            urls = input.urls,
            characters = characterEntityDataMapper.transformEntityList(input.characters),
            creators = creatorEntityDataMapper.transformEntityList(input.creators),
            favorite = input.favorite
        )
    }

    override fun transformModelToEntity(input: Comic): ComicEntity {
        return ComicEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            pageCount = input.pageCount,
            image = input.image,
            urls = input.urls,
            characters = characterEntityDataMapper.transformModelList(input.characters),
            creators = creatorEntityDataMapper.transformModelList(input.creators),
            favorite = input.favorite
        )
    }

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.MODEL_MAPPER_COMIC, "error", error)
    }

}
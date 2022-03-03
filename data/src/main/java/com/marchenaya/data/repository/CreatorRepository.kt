package com.marchenaya.data.repository

import com.marchenaya.data.business.CreatorBusinessHelper
import com.marchenaya.data.mapper.local.CreatorEntityDataMapper
import com.marchenaya.data.model.Creator
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CreatorRepository @Inject constructor(
    private val creatorBusinessHelper: CreatorBusinessHelper,
    private val creatorEntityDataMapper: CreatorEntityDataMapper
) {

    suspend fun getCreatorListById(comicId: Int): List<Creator> =
        creatorEntityDataMapper.transformEntityList(
            creatorBusinessHelper.getCreatorListById(
                comicId
            )
        )

}
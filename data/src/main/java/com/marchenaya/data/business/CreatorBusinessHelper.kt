package com.marchenaya.data.business

import com.marchenaya.data.entity.local.CreatorEntity
import com.marchenaya.data.manager.api.ApiManager
import com.marchenaya.data.mapper.remote.CreatorRemoteEntityDataMapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CreatorBusinessHelper @Inject constructor(
    private val apiManager: ApiManager,
    private val creatorRemoteEntityDataMapper: CreatorRemoteEntityDataMapper
) {

    suspend fun getCreatorListByIdFromApi(comicId: Int): List<CreatorEntity> =
        creatorRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getCreatorsByComicId(
                comicId
            )
        )

}
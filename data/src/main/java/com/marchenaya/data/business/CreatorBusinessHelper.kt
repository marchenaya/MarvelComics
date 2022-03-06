package com.marchenaya.data.business

import com.marchenaya.data.entity.local.CreatorEntity
import com.marchenaya.data.manager.api.ApiManager
import com.marchenaya.data.manager.db.DBManager
import com.marchenaya.data.mapper.remote.CreatorRemoteEntityDataMapper
import com.marchenaya.data.model.Creator
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CreatorBusinessHelper @Inject constructor(
    private val apiManager: ApiManager,
    private val dbManager: DBManager,
    private val creatorRemoteEntityDataMapper: CreatorRemoteEntityDataMapper
) {

    suspend fun getCreatorListByIdFromApi(comicId: Int): List<CreatorEntity> =
        creatorRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getCreatorsByComicId(
                comicId
            )
        )

    suspend fun saveCreatorInDB(creatorList: List<Creator>) {
        dbManager.saveCreatorList(creatorList)
    }

    suspend fun getCreatorsByComicIdFromDB(comicId: Int): List<Creator> =
        dbManager.getCreatorsByComicId(comicId)

    suspend fun removeCreatorInDB(creatorList: List<Creator>) {
        dbManager.removeCreatorList(creatorList)
    }

}
package com.marchenaya.data.business

import com.marchenaya.data.entity.local.CharacterEntity
import com.marchenaya.data.manager.api.ApiManager
import com.marchenaya.data.mapper.remote.CharacterRemoteEntityDataMapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CharacterBusinessHelper @Inject constructor(
    private val apiManager: ApiManager,
    private val characterRemoteEntityDataMapper: CharacterRemoteEntityDataMapper
) {

    suspend fun getCharacterListById(comicId: Int): List<CharacterEntity> =
        characterRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getCharactersByComicId(
                comicId
            )
        )

}
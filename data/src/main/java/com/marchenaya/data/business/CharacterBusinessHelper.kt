package com.marchenaya.data.business

import com.marchenaya.data.entity.local.CharacterEntity
import com.marchenaya.data.manager.api.ApiManager
import com.marchenaya.data.manager.db.DBManager
import com.marchenaya.data.mapper.remote.CharacterRemoteEntityDataMapper
import com.marchenaya.data.model.Character
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CharacterBusinessHelper @Inject constructor(
    private val apiManager: ApiManager,
    private val dbManager: DBManager,
    private val characterRemoteEntityDataMapper: CharacterRemoteEntityDataMapper
) {

    suspend fun getCharacterListByIdFromApi(comicId: Int): List<CharacterEntity> =
        characterRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getCharactersByComicId(
                comicId
            )
        )

    suspend fun saveCharacterInDB(characterList: List<Character>) {
        dbManager.saveCharacterList(characterList)
    }

    suspend fun getCharactersByComicIdFromDB(comicId: Int): List<Character> =
        dbManager.getCharactersByComicId(comicId)

    suspend fun removeCharacterInDB(characterList: List<Character>) {
        dbManager.removeCharacterList(characterList)
    }

}
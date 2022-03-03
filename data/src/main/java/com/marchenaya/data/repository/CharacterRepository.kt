package com.marchenaya.data.repository

import com.marchenaya.data.business.CharacterBusinessHelper
import com.marchenaya.data.mapper.local.CharacterEntityDataMapper
import com.marchenaya.data.model.Character
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CharacterRepository @Inject constructor(
    private val characterBusinessHelper: CharacterBusinessHelper,
    private val characterEntityDataMapper: CharacterEntityDataMapper
) {

    suspend fun getCharacterListById(comicId: Int): List<Character> =
        characterEntityDataMapper.transformEntityList(
            characterBusinessHelper.getCharacterListById(
                comicId
            )
        )

}
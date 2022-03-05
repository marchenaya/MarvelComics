package com.marchenaya.data.manager.api

import com.marchenaya.data.entity.remote.CharacterRemoteEntity
import com.marchenaya.data.entity.remote.ComicRemoteEntity
import com.marchenaya.data.entity.remote.CreatorRemoteEntity

interface ApiManager {

    suspend fun getComics(page: Int, itemsPerPage: Int): List<ComicRemoteEntity>

    suspend fun getComicsByTitle(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): List<ComicRemoteEntity>

    suspend fun getCharactersByComicId(comicId: Int): List<CharacterRemoteEntity>

    suspend fun getCreatorsByComicId(comicId: Int): List<CreatorRemoteEntity>

    suspend fun getComicById(comicId: Int): ComicRemoteEntity
}
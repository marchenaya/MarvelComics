package com.marchenaya.data.manager.api

import com.marchenaya.data.entity.remote.CharacterRemoteEntity
import com.marchenaya.data.entity.remote.ComicRemoteEntity
import com.marchenaya.data.entity.remote.CreatorRemoteEntity
import com.marchenaya.data.manager.api.service.MarvelApiRetrofitService
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(private val marvelApiRetrofitService: MarvelApiRetrofitService) :
    ApiManager {

    override suspend fun getComics(page: Int, itemsPerPage: Int): List<ComicRemoteEntity> {
        return marvelApiRetrofitService.getComics(page, itemsPerPage).data.results
    }

    override suspend fun getComicsByTitle(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): List<ComicRemoteEntity> {
        return marvelApiRetrofitService.getComicsByTitle(query, page, itemsPerPage).data.results
    }

    override suspend fun getCharactersByComicId(comicId: Int): List<CharacterRemoteEntity> {
        return marvelApiRetrofitService.getCharactersByComicId(comicId).data.results
    }

    override suspend fun getCreatorsByComicId(comicId: Int): List<CreatorRemoteEntity> {
        return marvelApiRetrofitService.getCreatorsByComicId(comicId).data.results
    }

    override suspend fun getComicById(comicId: Int): ComicRemoteEntity {
        return marvelApiRetrofitService.getComicById(comicId).data.results[0]
    }

}
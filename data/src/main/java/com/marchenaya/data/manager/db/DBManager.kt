package com.marchenaya.data.manager.db

import androidx.paging.PagingSource
import com.marchenaya.data.entity.db.CharacterDBEntity
import com.marchenaya.data.entity.db.ComicDBEntity
import com.marchenaya.data.entity.db.CreatorDBEntity
import com.marchenaya.data.entity.db.UrlDBEntity

interface DBManager {

    suspend fun saveComic(comicDBEntity: ComicDBEntity)
    suspend fun getComicById(comicId: Int): ComicDBEntity?
    fun getComicsByTitle(title: String): PagingSource<Int, ComicDBEntity>
    suspend fun getComicsByTitleList(title: String): List<ComicDBEntity>
    suspend fun removeComic(comicDBEntity: ComicDBEntity)

    suspend fun saveCharacterList(characterDBEntityList: List<CharacterDBEntity>)
    suspend fun getCharactersByComicId(comicId: Int): List<CharacterDBEntity>
    suspend fun removeCharacterList(characterDBEntityList: List<CharacterDBEntity>)

    suspend fun saveCreatorList(creatorDBEntityList: List<CreatorDBEntity>)
    suspend fun getCreatorsByComicId(comicId: Int): List<CreatorDBEntity>
    suspend fun removeCreatorList(creatorDBEntityList: List<CreatorDBEntity>)

    suspend fun saveUrlList(urlDBEntityList: List<UrlDBEntity>)
    suspend fun getUrlsByComicId(comicId: Int): List<UrlDBEntity>
    suspend fun removeUrlList(urlDBEntityList: List<UrlDBEntity>)

}
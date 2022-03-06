package com.marchenaya.data.manager.db

import androidx.paging.PagingSource
import com.marchenaya.data.model.Character
import com.marchenaya.data.model.Comic
import com.marchenaya.data.model.ComicKey
import com.marchenaya.data.model.ComicUrl
import com.marchenaya.data.model.Creator

interface DBManager {

    suspend fun saveComic(comic: Comic)
    suspend fun getComicById(comicId: Int): Comic
    fun getComicsByTitle(title: String): PagingSource<Int, Comic>
    suspend fun removeComic(comic: Comic)

    suspend fun saveCharacterList(characterList: List<Character>)
    suspend fun getCharactersByComicId(comicId: Int): List<Character>
    suspend fun removeCharacterList(characterList: List<Character>)

    suspend fun saveCreatorList(creatorList: List<Creator>)
    suspend fun getCreatorsByComicId(comicId: Int): List<Creator>
    suspend fun removeCreatorList(creatorList: List<Creator>)

    suspend fun saveUrlList(comicUrlList: List<ComicUrl>)
    suspend fun getUrlsByComicId(comicId: Int): List<ComicUrl>
    suspend fun removeUrlList(comicUrlList: List<ComicUrl>)

    suspend fun saveKey(comicKey: ComicKey)
    suspend fun getKeyByComicId(comicId: Int): ComicKey
    suspend fun removeKey(comicKey: ComicKey)

}
package com.marchenaya.data.manager.db

import androidx.paging.PagingSource
import com.marchenaya.data.model.Character
import com.marchenaya.data.model.Comic
import com.marchenaya.data.model.ComicKey
import com.marchenaya.data.model.ComicUrl
import com.marchenaya.data.model.Creator
import javax.inject.Inject

class DBManagerImpl @Inject constructor(private val database: MarvelDatabase) : DBManager {

    override suspend fun saveComic(comic: Comic) {
        database.comicDao().saveComic(comic)
    }

    override suspend fun getComicById(comicId: Int): Comic =
        database.comicDao().getComicById(comicId)

    override fun getComicsByTitle(title: String): PagingSource<Int, Comic> =
        database.comicDao().getComicsByTitle(title)

    override suspend fun removeComic(comic: Comic) {
        database.comicDao().removeComic(comic)
    }

    override suspend fun saveCharacterList(characterList: List<Character>) {
        database.characterDao().saveCharacterList(characterList)
    }

    override suspend fun getCharactersByComicId(comicId: Int): List<Character> =
        database.characterDao().getCharactersByComicId(comicId)

    override suspend fun removeCharacterList(characterList: List<Character>) {
        database.characterDao().removeCharacterList(characterList)
    }

    override suspend fun saveCreatorList(creatorList: List<Creator>) {
        database.creatorDao().removeCreatorList(creatorList)
    }

    override suspend fun getCreatorsByComicId(comicId: Int): List<Creator> =
        database.creatorDao().getCreatorsByComicId(comicId)

    override suspend fun removeCreatorList(creatorList: List<Creator>) {
        database.creatorDao().removeCreatorList(creatorList)
    }

    override suspend fun saveUrlList(comicUrlList: List<ComicUrl>) {
        database.urlDao().saveUrlList(comicUrlList)
    }

    override suspend fun getUrlsByComicId(comicId: Int): List<ComicUrl> =
        database.urlDao().getUrlsByComicId(comicId)

    override suspend fun removeUrlList(comicUrlList: List<ComicUrl>) {
        database.urlDao().removeUrlList(comicUrlList)
    }

    override suspend fun saveKey(comicKey: ComicKey) {
        database.keyDao().saveKey(comicKey)
    }

    override suspend fun getKeyByComicId(comicId: Int): ComicKey =
        database.keyDao().getKeyByComicId(comicId)

    override suspend fun removeKey(comicKey: ComicKey) {
        database.keyDao().removeKey(comicKey)
    }
}
package com.marchenaya.data.manager.db

import androidx.paging.PagingSource
import com.marchenaya.data.entity.db.CharacterDBEntity
import com.marchenaya.data.entity.db.ComicDBEntity
import com.marchenaya.data.entity.db.CreatorDBEntity
import com.marchenaya.data.entity.db.UrlDBEntity
import javax.inject.Inject

class DBManagerImpl @Inject constructor(private val database: MarvelDatabase) : DBManager {

    override suspend fun saveComic(comicDBEntity: ComicDBEntity) {
        database.comicDao().saveComic(comicDBEntity)
    }

    override suspend fun getComicById(comicId: Int): ComicDBEntity? =
        database.comicDao().getComicById(comicId)

    override fun getComicsByTitle(title: String): PagingSource<Int, ComicDBEntity> =
        database.comicDao().getComicsByTitle(title)

    override suspend fun getComicsByTitleList(title: String): List<ComicDBEntity> =
        database.comicDao().getComicsByTitleList(title)

    override suspend fun removeComic(comicDBEntity: ComicDBEntity) {
        database.comicDao().removeComic(comicDBEntity)
    }

    override suspend fun saveCharacterList(characterDBEntityList: List<CharacterDBEntity>) {
        database.characterDao().saveCharacterList(characterDBEntityList)
    }

    override suspend fun getCharactersByComicId(comicId: Int): List<CharacterDBEntity> =
        database.characterDao().getCharactersByComicId(comicId)

    override suspend fun removeCharacterList(characterDBEntityList: List<CharacterDBEntity>) {
        database.characterDao().removeCharacterList(characterDBEntityList)
    }

    override suspend fun saveCreatorList(creatorDBEntityList: List<CreatorDBEntity>) {
        database.creatorDao().saveCreatorList(creatorDBEntityList)
    }

    override suspend fun getCreatorsByComicId(comicId: Int): List<CreatorDBEntity> =
        database.creatorDao().getCreatorsByComicId(comicId)

    override suspend fun removeCreatorList(creatorDBEntityList: List<CreatorDBEntity>) {
        database.creatorDao().removeCreatorList(creatorDBEntityList)
    }

    override suspend fun saveUrlList(urlDBEntityList: List<UrlDBEntity>) {
        database.urlDao().saveUrlList(urlDBEntityList)
    }

    override suspend fun getUrlsByComicId(comicId: Int): List<UrlDBEntity> =
        database.urlDao().getUrlsByComicId(comicId)

    override suspend fun removeUrlList(urlDBEntityList: List<UrlDBEntity>) {
        database.urlDao().removeUrlList(urlDBEntityList)
    }

}
package com.marchenaya.data.business

import androidx.paging.PagingSource
import com.marchenaya.data.entity.local.ComicEntity
import com.marchenaya.data.manager.api.ApiManager
import com.marchenaya.data.manager.db.DBManager
import com.marchenaya.data.mapper.db.CharacterDBEntityDataMapper
import com.marchenaya.data.mapper.db.ComicDBEntityDataMapper
import com.marchenaya.data.mapper.db.CreatorDBEntityDataMapper
import com.marchenaya.data.mapper.db.UrlDBEntityDataMapper
import com.marchenaya.data.mapper.remote.CharacterRemoteEntityDataMapper
import com.marchenaya.data.mapper.remote.ComicRemoteEntityDataMapper
import com.marchenaya.data.mapper.remote.CreatorRemoteEntityDataMapper
import com.marchenaya.data.model.Comic
import com.marchenaya.data.model.ComicKey
import com.marchenaya.data.model.ComicUrl
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ComicBusinessHelper @Inject constructor(
    private val apiManager: ApiManager,
    private val dbManager: DBManager,
    private val comicRemoteEntityDataMapper: ComicRemoteEntityDataMapper,
    private val characterRemoteEntityDataMapper: CharacterRemoteEntityDataMapper,
    private val creatorRemoteEntityDataMapper: CreatorRemoteEntityDataMapper,
    private val comicDBEntityDataMapper: ComicDBEntityDataMapper,
    private val characterDBEntityDataMapper: CharacterDBEntityDataMapper,
    private val creatorDBEntityDataMapper: CreatorDBEntityDataMapper,
    private val urlDBEntityDataMapper: UrlDBEntityDataMapper
) {

    suspend fun getComicListFromApi(page: Int, itemsPerPage: Int): List<ComicEntity> =
        comicRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getComics(
                page, itemsPerPage
            )
        )

    suspend fun getComicListByTitleFromApi(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): List<ComicEntity> =
        comicRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getComicsByTitle(
                query, page, itemsPerPage
            )
        )

    suspend fun getComicByIdFromApi(comicId: Int): ComicEntity {
        val comicEntity = comicRemoteEntityDataMapper.transformRemoteToEntity(
            apiManager.getComicById(comicId)
        )

        return comicEntity.copy(
            characters = characterRemoteEntityDataMapper.transformRemoteEntityList(
                apiManager.getCharactersByComicId(comicId)
            ),
            creators = creatorRemoteEntityDataMapper.transformRemoteEntityList(
                apiManager.getCreatorsByComicId(comicId)
            )
        )
    }

    suspend fun saveComicInDB(comic: Comic) {
        dbManager.saveCharacterList(comic.characters)
        dbManager.saveCreatorList(comic.creators)
        dbManager.saveUrlList(comic.urls.map { ComicUrl(comicId = comic.id, url = it) })
        dbManager.saveKey(ComicKey(comic.id, comic.prevKey, comic.nextKey))
        dbManager.saveComic(comic)
    }

    suspend fun getComicByIdFromDB(comicId: Int): Comic =
        dbManager.getComicById(comicId)

    fun getComicsByTitle(query: String): PagingSource<Int, Comic> =
        dbManager.getComicsByTitle(query)

    suspend fun removeComic(comic: Comic) {
        dbManager.removeCharacterList(comic.characters)
        dbManager.removeCreatorList(comic.creators)
        dbManager.removeUrlList(comic.urls.map { ComicUrl(comicId = comic.id, url = it) })
        dbManager.removeKey(ComicKey(comic.id, comic.prevKey, comic.nextKey))
        dbManager.removeComic(comic)
    }

    suspend fun getKeyByComicId(comicId: Int): ComicKey =
        dbManager.getKeyByComicId(comicId)


}
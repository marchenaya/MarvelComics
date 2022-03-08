package com.marchenaya.data.business

import com.marchenaya.data.entity.db.UrlDBEntity
import com.marchenaya.data.entity.local.ComicEntity
import com.marchenaya.data.manager.api.ApiManager
import com.marchenaya.data.manager.db.DBManager
import com.marchenaya.data.mapper.db.CharacterDBEntityDataMapper
import com.marchenaya.data.mapper.db.ComicDBEntityDataMapper
import com.marchenaya.data.mapper.db.CreatorDBEntityDataMapper
import com.marchenaya.data.mapper.remote.CharacterRemoteEntityDataMapper
import com.marchenaya.data.mapper.remote.ComicRemoteEntityDataMapper
import com.marchenaya.data.mapper.remote.CreatorRemoteEntityDataMapper
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
    private val creatorDBEntityDataMapper: CreatorDBEntityDataMapper
) {

    suspend fun getComicListFromApi(position: Int, itemsPerPage: Int): List<ComicEntity> =
        comicRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getComics(
                position, itemsPerPage
            )
        )

    suspend fun getComicListByTitleFromApi(
        query: String,
        position: Int,
        itemsPerPage: Int
    ): List<ComicEntity> =
        comicRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getComicsByTitle(
                query, position, itemsPerPage
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

    suspend fun saveComicInDB(comic: ComicEntity) {
        dbManager.saveCharacterList(
            characterDBEntityDataMapper.transformEntityList(comic.characters)
                .map { it.copy(comicId = comic.id) })
        dbManager.saveCreatorList(
            creatorDBEntityDataMapper.transformEntityList(comic.creators)
                .map { it.copy(comicId = comic.id) })
        dbManager.saveUrlList(comic.urls.map { UrlDBEntity(0, comic.id, it) })
        dbManager.saveComic(comicDBEntityDataMapper.transformEntityToDB(comic))
    }

    suspend fun getComicByIdFromDB(comicId: Int): ComicEntity? {
        return dbManager.getComicById(comicId)
            ?.let { comicDBEntityDataMapper.transformDBToEntity(it) }?.copy(
                urls = dbManager.getUrlsByComicId(comicId).map { it.url },
                characters = characterDBEntityDataMapper.transformDBEntityList(
                    dbManager.getCharactersByComicId(comicId)
                ),
                creators = creatorDBEntityDataMapper.transformDBEntityList(
                    dbManager.getCreatorsByComicId(comicId)
                )
            )
    }

    suspend fun getComicsByTitleList(query: String): List<ComicEntity> =
        comicDBEntityDataMapper.transformDBEntityList(dbManager.getComicsByTitleList(query))

    suspend fun removeComic(comicEntity: ComicEntity) {
        dbManager.removeCharacterList(
            characterDBEntityDataMapper.transformEntityList(comicEntity.characters)
                .map { it.copy(comicId = comicEntity.id) })
        dbManager.removeCreatorList(
            creatorDBEntityDataMapper.transformEntityList(comicEntity.creators)
                .map { it.copy(comicId = comicEntity.id) })
        dbManager.removeUrlList(comicEntity.urls.map { UrlDBEntity(0, comicEntity.id, it) })
        dbManager.removeComic(comicDBEntityDataMapper.transformEntityToDB(comicEntity))
    }

}
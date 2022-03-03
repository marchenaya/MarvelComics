package com.marchenaya.data.business

import com.marchenaya.data.entity.local.ComicEntity
import com.marchenaya.data.manager.api.ApiManager
import com.marchenaya.data.mapper.remote.ComicRemoteEntityDataMapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ComicBusinessHelper @Inject constructor(
    private val apiManager: ApiManager,
    private val comicRemoteEntityDataMapper: ComicRemoteEntityDataMapper
) {

    suspend fun getComicList(page: Int, itemsPerPage: Int): List<ComicEntity> =
        comicRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getComics(
                page, itemsPerPage
            )
        )

    suspend fun getComicListByTitle(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): List<ComicEntity> =
        comicRemoteEntityDataMapper.transformRemoteEntityList(
            apiManager.getComicsByTitle(
                query, page, itemsPerPage
            )
        )

}
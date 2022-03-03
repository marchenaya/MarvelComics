package com.marchenaya.data.repository

import com.marchenaya.data.business.ComicBusinessHelper
import com.marchenaya.data.mapper.local.ComicEntityDataMapper
import com.marchenaya.data.model.Comic
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ComicRepository @Inject constructor(
    private val comicBusinessHelper: ComicBusinessHelper,
    private val comicEntityDataMapper: ComicEntityDataMapper
) {

    suspend fun getComicList(page: Int, itemsPerPage: Int): List<Comic> =
        comicEntityDataMapper.transformEntityList(
            comicBusinessHelper.getComicList(
                page, itemsPerPage
            )
        )

    suspend fun getComicListByTitle(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): List<Comic> =
        comicEntityDataMapper.transformEntityList(
            comicBusinessHelper.getComicListByTitle(
                query, page, itemsPerPage
            )
        )

}
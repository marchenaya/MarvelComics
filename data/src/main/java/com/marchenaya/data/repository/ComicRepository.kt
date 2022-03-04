package com.marchenaya.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marchenaya.data.BuildConfig.NETWORK_PAGE_SIZE
import com.marchenaya.data.business.ComicBusinessHelper
import com.marchenaya.data.mapper.local.ComicEntityDataMapper
import com.marchenaya.data.model.Comic
import com.marchenaya.data.paging.MarvelPagingSource
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@Reusable
class ComicRepository @Inject constructor(
    private val comicBusinessHelper: ComicBusinessHelper,
    private val comicEntityDataMapper: ComicEntityDataMapper
) {

    fun getComicList(): Flow<PagingData<Comic>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                MarvelPagingSource(
                    comicBusinessHelper,
                    comicEntityDataMapper
                )
            }).flow
    }

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
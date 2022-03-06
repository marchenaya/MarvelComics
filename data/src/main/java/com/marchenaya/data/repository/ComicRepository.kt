package com.marchenaya.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marchenaya.data.BuildConfig.NETWORK_PAGE_SIZE
import com.marchenaya.data.business.ComicBusinessHelper
import com.marchenaya.data.mapper.local.ComicEntityDataMapper
import com.marchenaya.data.model.Comic
import com.marchenaya.data.paging.MarvelPagingSource
import com.marchenaya.data.paging.MarvelRemoteMediator
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@Reusable
class ComicRepository @Inject constructor(
    private val comicBusinessHelper: ComicBusinessHelper,
    private val comicEntityDataMapper: ComicEntityDataMapper
) {

    fun getComicList(query: String, isNetworkAvailable: Boolean): Flow<PagingData<Comic>> {
        if (isNetworkAvailable) {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false,
                    initialLoadSize = NETWORK_PAGE_SIZE
                ),
                pagingSourceFactory = {
                    MarvelPagingSource(
                        query,
                        comicBusinessHelper,
                        comicEntityDataMapper
                    )
                }).flow
        } else {
            val dbQuery = "%${query.replace(' ', '%')}%"
            val pagingSourceFactory = { comicBusinessHelper.getComicsByTitle(dbQuery) }

            @OptIn(ExperimentalPagingApi::class)
            return Pager(
                config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
                remoteMediator = MarvelRemoteMediator(
                    query,
                    comicBusinessHelper
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow
        }
    }

    suspend fun getComicById(comicId: Int): Comic =
        comicEntityDataMapper.transformEntityToModel(comicBusinessHelper.getComicByIdFromApi(comicId))
}

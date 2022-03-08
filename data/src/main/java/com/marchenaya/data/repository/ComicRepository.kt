package com.marchenaya.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marchenaya.data.BuildConfig.NETWORK_PAGE_SIZE
import com.marchenaya.data.business.ComicBusinessHelper
import com.marchenaya.data.manager.network.NetworkManager
import com.marchenaya.data.mapper.local.ComicEntityDataMapper
import com.marchenaya.data.model.Comic
import com.marchenaya.data.paging.MarvelPagingSource
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@Reusable
class ComicRepository @Inject constructor(
    private val comicBusinessHelper: ComicBusinessHelper,
    private val comicEntityDataMapper: ComicEntityDataMapper,
    private val networkManager: NetworkManager
) {

    fun getComicList(query: String): Flow<PagingData<Comic>> {
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
                    comicEntityDataMapper,
                    networkManager
                )
            }).flow
    }

    suspend fun getComicByIdFromApi(comicId: Int): Comic =
        comicEntityDataMapper.transformEntityToModel(comicBusinessHelper.getComicByIdFromApi(comicId))

    suspend fun getComicByIdFromDB(comicId: Int): Comic? =
        comicBusinessHelper.getComicByIdFromDB(comicId)
            ?.let { comicEntityDataMapper.transformEntityToModel(it) }

    suspend fun saveComic(comic: Comic) {
        comicBusinessHelper.saveComicInDB(comicEntityDataMapper.transformModelToEntity(comic))
    }

    suspend fun removeComic(comic: Comic) {
        comicBusinessHelper.removeComic(comicEntityDataMapper.transformModelToEntity(comic))
    }

}

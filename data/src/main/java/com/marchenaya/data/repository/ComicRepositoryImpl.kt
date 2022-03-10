package com.marchenaya.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marchenaya.data.BuildConfig.NETWORK_PAGE_SIZE
import com.marchenaya.data.business.ComicBusinessHelper
import com.marchenaya.data.manager.paging.PagingSourceManager
import com.marchenaya.data.mapper.local.ComicEntityDataMapper
import com.marchenaya.domain.model.Comic
import com.marchenaya.domain.repository.ComicRepository
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

@Reusable
class ComicRepositoryImpl @Inject constructor(
    private val comicBusinessHelper: ComicBusinessHelper,
    private val comicEntityDataMapper: ComicEntityDataMapper,
    private val pagingSourceManager: PagingSourceManager
) : ComicRepository {

    override fun getComicList(query: String, filterByFavorite: Boolean): Flow<PagingData<Comic>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                pagingSourceManager.getPagingSource(query, filterByFavorite)
            }).flow
    }

    override suspend fun getComicById(comicId: Int): Comic {
        var comic = getComicByIdFromDB(comicId)
        if (comic == null) {
            comic = getComicByIdFromApi(comicId)
        } else if (comic.favorite) {
            comic = getComicByIdFromApi(comicId)
            saveComic(comic)
            Timber.i("Updated comic : ${comic.title}")
        }
        return comic
    }

    private suspend fun getComicByIdFromApi(comicId: Int): Comic =
        comicEntityDataMapper.transformEntityToModel(comicBusinessHelper.getComicByIdFromApi(comicId))

    private suspend fun getComicByIdFromDB(comicId: Int): Comic? =
        comicBusinessHelper.getComicByIdFromDB(comicId)
            ?.let { comicEntityDataMapper.transformEntityToModel(it) }

    override suspend fun saveComic(comic: Comic) {
        comicBusinessHelper.saveComicInDB(comicEntityDataMapper.transformModelToEntity(comic))
    }

    override suspend fun removeComic(comic: Comic) {
        comicBusinessHelper.removeComic(comicEntityDataMapper.transformModelToEntity(comic))
    }

}

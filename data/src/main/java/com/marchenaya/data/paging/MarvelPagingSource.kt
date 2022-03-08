package com.marchenaya.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marchenaya.data.business.ComicBusinessHelper
import com.marchenaya.data.manager.network.NetworkManager
import com.marchenaya.data.mapper.local.ComicEntityDataMapper
import com.marchenaya.data.model.Comic
import java.io.IOException
import retrofit2.HttpException
import timber.log.Timber

private const val MARVEL_STARTING_PAGE_INDEX = 0

class MarvelPagingSource(
    private val query: String,
    private val comicBusinessHelper: ComicBusinessHelper,
    private val comicEntityDataMapper: ComicEntityDataMapper,
    private val networkManager: NetworkManager
) : PagingSource<Int, Comic>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comic> {
        val position = params.key ?: MARVEL_STARTING_PAGE_INDEX
        return try {
            if (networkManager.checkInternetConnectivity()) {
                val response = if (query.isEmpty()) {
                    comicBusinessHelper.getComicListFromApi(position, params.loadSize)
                } else {
                    comicBusinessHelper.getComicListByTitleFromApi(query, position, params.loadSize)
                }
                val nextKey = if (response.isEmpty()) {
                    null
                } else {
                    position + params.loadSize
                }
                val prevKey =
                    if (position == MARVEL_STARTING_PAGE_INDEX) null else position - params.loadSize
                LoadResult.Page(
                    data = comicEntityDataMapper.transformEntityList(response),
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                val dbQuery = "%${query.replace(' ', '%')}%"
                val response = comicBusinessHelper.getComicsByTitleList(dbQuery)

                LoadResult.Page(
                    data = comicEntityDataMapper.transformEntityList(response),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (exception: IOException) {
            Timber.e(exception.message.toString())
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Timber.e(exception.message.toString())
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Comic>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
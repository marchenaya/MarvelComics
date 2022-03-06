package com.marchenaya.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.marchenaya.data.business.ComicBusinessHelper
import com.marchenaya.data.model.Comic
import com.marchenaya.data.model.ComicKey
import java.io.IOException
import retrofit2.HttpException

private const val MARVEL_STARTING_PAGE_INDEX = 0

@OptIn(ExperimentalPagingApi::class)
class MarvelRemoteMediator(
    private val query: String,
    private val comicBusinessHelper: ComicBusinessHelper
) : RemoteMediator<Int, Comic>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Comic>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val key = getRemoteKeyClosestToCurrentPosition(state)
                key?.nextKey?.minus(1) ?: MARVEL_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val key = getRemoteKeyForFirstItem(state)
                val prevKey = key?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = key != null)
                prevKey
            }
            LoadType.APPEND -> {
                val key = getRemoteKeyForLastItem(state)
                val nextKey = key?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = key != null)
                nextKey
            }
        }
        return try {
            val response = if (query.isEmpty()) {
                comicBusinessHelper.getComicListFromApi(page, state.config.pageSize)
            } else {
                comicBusinessHelper.getComicListByTitleFromApi(query, page, state.config.pageSize)
            }
            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Comic>): ComicKey? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { comic ->
                comicBusinessHelper.getKeyByComicId(comic.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Comic>): ComicKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { comic ->
                comicBusinessHelper.getKeyByComicId(comic.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Comic>): ComicKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { comicId ->
                comicBusinessHelper.getKeyByComicId(comicId)
            }
        }
    }

}
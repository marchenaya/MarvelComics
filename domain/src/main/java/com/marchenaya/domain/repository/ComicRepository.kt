package com.marchenaya.domain.repository

import androidx.paging.PagingData
import com.marchenaya.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface ComicRepository {

    fun getComicList(query: String, filterByFavorite: Boolean): Flow<PagingData<Comic>>
    suspend fun getComicById(comicId: Int): Comic
    suspend fun saveComic(comic: Comic)
    suspend fun removeComic(comic: Comic)

}

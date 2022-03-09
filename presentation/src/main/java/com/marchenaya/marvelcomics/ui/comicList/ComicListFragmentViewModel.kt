package com.marchenaya.marvelcomics.ui.comicList

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.marchenaya.data.model.Comic
import com.marchenaya.data.repository.ComicRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ComicListFragmentViewModel @Inject constructor(private val comicRepository: ComicRepository) :
    ViewModel() {

    private var currentQueryValue: String? = null
    private var currentFilterValue: Boolean? = null
    private var comicFlow: Flow<PagingData<Comic>>? = null

    fun getComics(query: String, filterByFavorite: Boolean): Flow<PagingData<Comic>> {
        val lastResult = comicFlow
        if (query == currentQueryValue && filterByFavorite == currentFilterValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = query
        currentFilterValue = filterByFavorite
        val newResult: Flow<PagingData<Comic>> =
            comicRepository.getComicList(query, filterByFavorite)
        comicFlow = newResult
        return newResult
    }

}
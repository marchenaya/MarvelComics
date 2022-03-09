package com.marchenaya.marvelcomics.ui.comicList

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.marchenaya.data.model.Comic
import com.marchenaya.data.repository.ComicRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class ComicListFragmentViewModel @Inject constructor(private val comicRepository: ComicRepository) :
    ViewModel() {

    private var currentQueryValue: String = ""
    private var currentFilterValue: Boolean = false
    private var comicFlow = emptyFlow<PagingData<Comic>>()

    fun getComics(query: String, filterByFavorite: Boolean): Flow<PagingData<Comic>> {
        val lastResult = comicFlow
        if (query == currentQueryValue && filterByFavorite == currentFilterValue && lastResult != emptyFlow<PagingData<Comic>>()) {
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
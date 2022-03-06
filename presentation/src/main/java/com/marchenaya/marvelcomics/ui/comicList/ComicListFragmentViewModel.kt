package com.marchenaya.marvelcomics.ui.comicList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marchenaya.data.model.Comic
import com.marchenaya.data.repository.ComicRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ComicListFragmentViewModel @Inject constructor(private val comicRepository: ComicRepository) :
    ViewModel() {

    private var currentQueryValue: String? = null
    private var comicFlow: Flow<PagingData<Comic>>? = null

    fun getComics(query: String, isNetworkAvailable: Boolean): Flow<PagingData<Comic>> {
        val lastResult = comicFlow
        if (query == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = query
        val newResult: Flow<PagingData<Comic>> =
            comicRepository.getComicList(query, isNetworkAvailable)
                .cachedIn(viewModelScope)
        comicFlow = newResult
        return newResult
    }

}
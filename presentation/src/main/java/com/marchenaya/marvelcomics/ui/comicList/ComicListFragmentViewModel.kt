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

    private var comicFlow: Flow<PagingData<Comic>>? = null

    fun getComics(): Flow<PagingData<Comic>> {
        val lastResult = comicFlow
        if (lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<Comic>> = comicRepository.getComicList()
            .cachedIn(viewModelScope)
        comicFlow = newResult
        return newResult
    }

}
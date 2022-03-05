package com.marchenaya.marvelcomics.ui.comicDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marchenaya.data.repository.ComicRepository
import com.marchenaya.marvelcomics.base.SingleLiveEvent
import com.marchenaya.marvelcomics.wrapper.ComicDataWrapper
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

class ComicDetailFragmentViewModel @Inject constructor(private val comicRepository: ComicRepository) :
    ViewModel() {

    private val comicLiveData = MutableLiveData<ComicDataWrapper>()
    private val errorLiveEvent = SingleLiveEvent<Throwable>()

    fun getComicLiveData(): LiveData<ComicDataWrapper> = comicLiveData

    fun getErrorLiveEvent(): LiveData<Throwable> = errorLiveEvent

    fun retrieveComicDetail(id: Int) {
        viewModelScope.launch {
            try {
                val comic = comicRepository.getComicById(id)
                comicLiveData.postValue(ComicDataWrapper(comic))
            } catch (e: Exception) {
                Timber.e(e)
                errorLiveEvent.postValue(e)
            }
        }
    }

}
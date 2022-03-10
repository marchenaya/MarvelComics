package com.marchenaya.marvelcomics.ui.comicDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marchenaya.domain.model.Comic
import com.marchenaya.domain.useCase.comic.RemoveComic
import com.marchenaya.domain.useCase.comic.RetrieveComicById
import com.marchenaya.domain.useCase.comic.SaveComic
import com.marchenaya.marvelcomics.base.SingleLiveEvent
import com.marchenaya.marvelcomics.wrapper.ComicDataWrapper
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

class ComicDetailFragmentViewModel @Inject constructor(
    private val retrieveComicById: RetrieveComicById,
    private val saveComic: SaveComic,
    private val removeComic: RemoveComic
) : ViewModel() {

    private lateinit var currentComic: Comic

    private val comicLiveData = MutableLiveData<ComicDataWrapper>()
    private val savedComicLiveEvent = SingleLiveEvent<String>()
    private val removedComicLiveEvent = SingleLiveEvent<String>()
    private val errorLiveEvent = SingleLiveEvent<Exception>()

    fun getComicLiveData(): LiveData<ComicDataWrapper> = comicLiveData
    fun getSavedComicLiveEvent(): LiveData<String> = savedComicLiveEvent
    fun getRemovedComicLiveEvent(): LiveData<String> = removedComicLiveEvent
    fun getErrorLiveEvent(): LiveData<Exception> = errorLiveEvent

    fun retrieveComicDetail(id: Int) {
        viewModelScope.launch {
            try {
                currentComic =
                    retrieveComicById.launchUseCase(params = RetrieveComicById.Params(id))
                comicLiveData.postValue(ComicDataWrapper(currentComic))
            } catch (e: Exception) {
                Timber.e(e)
                errorLiveEvent.postValue(e)
            }
        }
    }

    fun setFavorite(): Boolean {
        return if (currentComic.favorite) {
            viewModelScope.launch {
                try {
                    currentComic = currentComic.copy(favorite = false)
                    removeComic.launchUseCase(RemoveComic.Params(comic = currentComic))
                    Timber.i("Removed comic : ${currentComic.title}")
                    removedComicLiveEvent.postValue(currentComic.title)
                } catch (e: Exception) {
                    Timber.e(e)
                    errorLiveEvent.postValue(e)
                }
            }
            false
        } else {
            viewModelScope.launch {
                try {
                    currentComic = currentComic.copy(favorite = true)
                    saveComic.launchUseCase(SaveComic.Params(comic = currentComic))
                    Timber.i("Saved comic : ${currentComic.title}")
                    savedComicLiveEvent.postValue(currentComic.title)
                } catch (e: Exception) {
                    Timber.e(e)
                    errorLiveEvent.postValue(e)
                }
            }
            true
        }
    }

}
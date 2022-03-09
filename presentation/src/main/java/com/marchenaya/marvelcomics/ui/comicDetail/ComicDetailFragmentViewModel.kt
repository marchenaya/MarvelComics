package com.marchenaya.marvelcomics.ui.comicDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marchenaya.data.model.Comic
import com.marchenaya.data.repository.ComicRepository
import com.marchenaya.marvelcomics.base.SingleLiveEvent
import com.marchenaya.marvelcomics.component.network.NetworkManager
import com.marchenaya.marvelcomics.wrapper.ComicDataWrapper
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

class ComicDetailFragmentViewModel @Inject constructor(
    private val comicRepository: ComicRepository,
    private val networkManager: NetworkManager
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
                val comicDB = comicRepository.getComicByIdFromDB(id)
                var comic: Comic? = null

                if (networkManager.checkInternetConnectivity()) {
                    if (comic != null && comicDB?.favorite == true) {
                        comicRepository.saveComic(comic)
                    } else {
                        comic = comicRepository.getComicByIdFromApi(id)
                    }
                } else {
                    if (comicDB != null) {
                        comic = comicDB
                    }
                }
                if (comic != null) {
                    currentComic = comic
                }
                comicLiveData.postValue(comic?.let { ComicDataWrapper(it) })
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
                    comicRepository.removeComic(currentComic)
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
                    comicRepository.saveComic(currentComic)
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
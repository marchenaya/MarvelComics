package com.marchenaya.marvelcomics.ui.comicDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marchenaya.data.model.Comic
import com.marchenaya.data.repository.ComicRepository
import com.marchenaya.marvelcomics.component.network.NetworkManager
import com.marchenaya.marvelcomics.wrapper.ComicDataWrapper
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

class ComicDetailFragmentViewModel @Inject constructor(
    private val comicRepository: ComicRepository,
    private val networkManager: NetworkManager
) :
    ViewModel() {

    private val comicLiveData = MutableLiveData<ComicDataWrapper>()

    private lateinit var currentComic: Comic
    private val isCurrentComicFavorite = MutableLiveData<Boolean>()

    fun getComicLiveData(): LiveData<ComicDataWrapper> = comicLiveData
    fun isCurrentComicFavorite(): LiveData<Boolean> = isCurrentComicFavorite

    fun retrieveComicDetail(id: Int) {

        viewModelScope.launch {
            try {
                val comicDB = comicRepository.getComicByIdFromDB(id)
                var comic: Comic? = null

                if (networkManager.checkInternetConnectivity()) {
                    if (comicDB?.favorite == true) {
                        comic = comicRepository.getComicByIdFromApi(id).copy(favorite = true)
                        comicRepository.saveComic(comic)
                        isCurrentComicFavorite.postValue(true)
                    } else {
                        comic = comicRepository.getComicByIdFromApi(id)
                        isCurrentComicFavorite.postValue(false)
                    }
                } else {
                    if (comicDB != null) {
                        comic = comicDB
                        isCurrentComicFavorite.postValue(true)
                    }
                }
                if (comic != null) {
                    currentComic = comic
                }
                comicLiveData.postValue(comic?.let { ComicDataWrapper(it) })
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun changeFavorite(): Boolean {
        return if (currentComic.favorite) {
            viewModelScope.launch {
                currentComic = currentComic.copy(favorite = false)
                isCurrentComicFavorite.postValue(false)
                comicRepository.removeComic(currentComic)
            }
            false
        } else {
            viewModelScope.launch {
                currentComic = currentComic.copy(favorite = true)
                isCurrentComicFavorite.postValue(true)
                comicRepository.saveComic(currentComic)
            }
            true
        }
    }

}
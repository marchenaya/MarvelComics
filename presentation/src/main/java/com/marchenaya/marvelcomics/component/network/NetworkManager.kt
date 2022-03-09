package com.marchenaya.marvelcomics.component.network

import androidx.lifecycle.MutableLiveData

interface NetworkManager {

    fun checkInternetConnectivity(): Boolean

    fun getConnectivityManager(): MutableLiveData<Boolean>

}
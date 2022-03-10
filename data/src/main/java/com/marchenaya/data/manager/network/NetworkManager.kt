package com.marchenaya.data.manager.network

import androidx.lifecycle.MutableLiveData

interface NetworkManager {

    fun checkInternetConnectivity(): Boolean

    fun getConnectivityManager(): MutableLiveData<Boolean>

}
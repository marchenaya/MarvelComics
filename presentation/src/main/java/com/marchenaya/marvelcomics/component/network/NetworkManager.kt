package com.marchenaya.marvelcomics.component.network

import android.content.Context
import android.net.ConnectivityManager

interface NetworkManager {

    fun checkInternetConnectivity(): Boolean

    fun getConnectivityManager(
        context: Context,
        onAvailableNetwork: () -> Unit,
        onLostNetwork: () -> Unit
    ): ConnectivityManager

}
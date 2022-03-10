package com.marchenaya.data.manager.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class NetworkManagerImpl @Inject constructor(private val context: Context) : NetworkManager {

    private val networkStatus = MutableLiveData<Boolean>()

    override fun checkInternetConnectivity(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
        return false
    }

    override fun getConnectivityManager(): MutableLiveData<Boolean> {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        val connectivityManager =
            getSystemService(context, ConnectivityManager::class.java) as ConnectivityManager

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkStatus.postValue(true)
            }

            override fun onLost(network: Network) {
                networkStatus.postValue(false)
                super.onLost(network)
            }
        }
        connectivityManager.requestNetwork(networkRequest, networkCallback)
        return networkStatus
    }

}
package com.marchenaya.marvelcomics.component.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class NetworkManagerImpl @Inject constructor(private val context: Context) : NetworkManager {

    private val netStatus = MutableLiveData<Boolean>()
    private var valToPost = false

    var wifiAvailable = false
    var cellularAvailable = false

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
//                val capabilities = connectivityManager.getNetworkCapabilities(network)
//                if (capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true){
//                    wifiAvailable = true
//                }
//                if (capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true){
//                    cellularAvailable = true
//                }
//                if (!(wifiAvailable && cellularAvailable) && !valToPost) {
//                    valToPost = true
                netStatus.postValue(valToPost)
//                }
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
//                  if (!networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
//                    wifiAvailable = false
//                      valToPost = false
//                }
                wifiAvailable = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                cellularAvailable =
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
//                if(!networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
//                    cellularAvailable = false
//                    valToPost = false
//                }
                super.onCapabilitiesChanged(network, networkCapabilities)

            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                if (capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
                    wifiAvailable = false
                }
                if (capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true) {
                    cellularAvailable = false
                }
                if (!wifiAvailable && !cellularAvailable) {
//                if(valToPost){
                    valToPost = false
                    netStatus.postValue(valToPost)
                }
                super.onLosing(network, maxMsToLive)
            }

            override fun onLost(network: Network) {
//                val capabilities = connectivityManager.getNetworkCapabilities(network)
//                if (capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == false || capabilities == null) {
//                    wifiAvailable = false
//                }
//                if (capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == false || capabilities == null) {
//                    cellularAvailable = false
//                }
//                if (!wifiAvailable && !cellularAvailable) {
//                if(valToPost){
                //valToPost = false
                netStatus.postValue(valToPost)
                // }
                super.onLost(network)
            }
        }
        connectivityManager.requestNetwork(networkRequest, networkCallback)
        return netStatus
    }


}
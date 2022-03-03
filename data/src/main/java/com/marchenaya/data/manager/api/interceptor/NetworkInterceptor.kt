package com.marchenaya.data.manager.api.interceptor

import com.marchenaya.data.exception.InvalidParametersException
import com.marchenaya.data.exception.OfflineException
import com.marchenaya.data.exception.RequestFailException
import dagger.Reusable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

@Reusable
class NetworkInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        try {
            val response = chain.proceed(request)
            checkResponse(response)
            return response
        } catch (exception: Exception) {
            when (exception) {
                is SocketTimeoutException,
                is TimeoutException,
                is ConnectException,
                is UnknownHostException -> throw OfflineException()
                else -> throw exception
            }
        }
    }

    private fun checkResponse(response: Response) {
        if (!response.isSuccessful) {
            when (response.code) {
                409 -> throw InvalidParametersException()
                else -> throw RequestFailException()
            }
        }
    }

}
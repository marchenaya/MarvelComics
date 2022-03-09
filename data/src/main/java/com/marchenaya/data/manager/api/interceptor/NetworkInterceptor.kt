package com.marchenaya.data.manager.api.interceptor

import com.marchenaya.data.exception.InvalidParametersException
import com.marchenaya.data.exception.NotFoundException
import com.marchenaya.data.exception.RequestFailException
import dagger.Reusable
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
            throw exception
        }
    }

    private fun checkResponse(response: Response) {
        if (!response.isSuccessful) {
            when (response.code) {
                409 -> throw InvalidParametersException()
                404 -> throw NotFoundException()
                else -> throw RequestFailException()
            }
        }
    }

}
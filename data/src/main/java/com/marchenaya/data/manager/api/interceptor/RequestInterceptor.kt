package com.marchenaya.data.manager.api.interceptor

import com.marchenaya.data.BuildConfig
import dagger.Reusable
import java.security.MessageDigest
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

@Reusable
class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val timeStamp = System.currentTimeMillis()
        val input =
            "${timeStamp}${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}"
        val hash = MessageDigest.getInstance("MD5")
            .digest(input.toByteArray()).joinToString("") {
                "%02x".format(it)
            }

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", "$timeStamp")
            .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
            .addQueryParameter("hash", hash)
            .build()

        val requestBuilder = originalRequest.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
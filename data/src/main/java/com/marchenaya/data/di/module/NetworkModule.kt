package com.marchenaya.data.di.module

import com.google.gson.GsonBuilder
import com.marchenaya.data.BuildConfig
import com.marchenaya.data.manager.api.interceptor.NetworkInterceptor
import com.marchenaya.data.manager.api.interceptor.RequestInterceptor
import com.marchenaya.data.manager.api.service.MarvelApiRetrofitService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun retrofitService(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory
    ): MarvelApiRetrofitService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()
            .create(MarvelApiRetrofitService::class.java)

    @Provides
    @Reusable
    fun provideApiOkHttp(
        loggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkInterceptor,
        requestInterceptor: RequestInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(requestInterceptor)
        builder.addInterceptor(networkInterceptor)
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    @Reusable
    fun loggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Reusable
    fun provideGsonConverter(): Converter.Factory =
        GsonConverterFactory.create(GsonBuilder().create())

}
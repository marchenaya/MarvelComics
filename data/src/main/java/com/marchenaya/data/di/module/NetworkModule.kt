package com.marchenaya.data.di.module

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
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun retrofitService(
        okHttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory,
        converter: Converter.Factory
    ): MarvelApiRetrofitService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
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
}
package com.marchenaya.data.di.module

import com.marchenaya.data.manager.api.ApiManager
import com.marchenaya.data.manager.api.ApiManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ManagerModule {

    @Binds
    @Singleton
    abstract fun provideApiManager(apiManagerImpl: ApiManagerImpl): ApiManager

}

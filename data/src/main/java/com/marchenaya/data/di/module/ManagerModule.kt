package com.marchenaya.data.di.module

import com.marchenaya.data.manager.api.ApiManager
import com.marchenaya.data.manager.api.ApiManagerImpl
import com.marchenaya.data.manager.db.DBManager
import com.marchenaya.data.manager.db.DBManagerImpl
import com.marchenaya.data.manager.network.NetworkManager
import com.marchenaya.data.manager.network.NetworkManagerImpl
import com.marchenaya.data.manager.paging.PagingSourceManager
import com.marchenaya.data.manager.paging.PagingSourceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ManagerModule {

    @Provides
    @Reusable
    fun apiManager(apiManagerImpl: ApiManagerImpl): ApiManager = apiManagerImpl

    @Provides
    @Reusable
    fun dbManager(dbManagerImpl: DBManagerImpl): DBManager = dbManagerImpl

    @Provides
    @Reusable
    fun networkManager(networkManagerImpl: NetworkManagerImpl): NetworkManager = networkManagerImpl

    @Provides
    @Reusable
    fun pagingSourceManager(pagingSourceManagerImpl: PagingSourceManagerImpl): PagingSourceManager =
        pagingSourceManagerImpl

}

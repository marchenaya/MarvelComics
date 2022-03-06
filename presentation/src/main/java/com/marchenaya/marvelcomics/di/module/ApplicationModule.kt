package com.marchenaya.marvelcomics.di.module

import com.marchenaya.marvelcomics.component.navigation.NavigationComponent
import com.marchenaya.marvelcomics.component.navigation.NavigationComponentImpl
import com.marchenaya.marvelcomics.component.network.NetworkManager
import com.marchenaya.marvelcomics.component.network.NetworkManagerImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object ApplicationModule {

    @Provides
    @Reusable
    fun navigationComponent(navigationComponent: NavigationComponentImpl): NavigationComponent =
        navigationComponent

    @Provides
    @Reusable
    fun networkManager(networkManager: NetworkManagerImpl): NetworkManager =
        networkManager

}
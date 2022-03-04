package com.marchenaya.data.di

import android.content.Context
import com.marchenaya.data.di.module.DataModule
import com.marchenaya.data.di.module.ManagerModule
import com.marchenaya.data.di.module.NetworkModule
import com.marchenaya.data.repository.ComicRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ManagerModule::class, NetworkModule::class])
interface DataComponent {

    fun comicRepository(): ComicRepository

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataComponent
    }
}

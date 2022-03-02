package com.marchenaya.data.di

import android.content.Context
import com.marchenaya.data.di.module.DataModule
import com.marchenaya.data.di.module.ManagerModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ManagerModule::class])
interface DataComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataComponent
    }
}
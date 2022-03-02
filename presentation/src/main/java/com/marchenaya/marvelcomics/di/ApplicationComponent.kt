package com.marchenaya.marvelcomics.di

import android.content.Context
import com.marchenaya.data.di.DataComponent
import com.marchenaya.marvelcomics.di.annotation.PerApplication
import com.marchenaya.marvelcomics.di.module.ActivityInjectorModule
import com.marchenaya.marvelcomics.di.module.ViewModelModule
import com.marchenaya.marvelcomics.ui.MarvelComicsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityInjectorModule::class,
        ViewModelModule::class
    ],
    dependencies = [
        DataComponent::class
    ]
)
interface ApplicationComponent {

    fun inject(application: MarvelComicsApplication)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            dataComponent: DataComponent
        ): ApplicationComponent
    }

}

package com.marchenaya.marvelcomics.ui

import android.app.Application
import com.marchenaya.data.di.DaggerDataComponent
import com.marchenaya.marvelcomics.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MarvelComicsApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.factory()
            .create(this, DaggerDataComponent.factory().create(this)).inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}

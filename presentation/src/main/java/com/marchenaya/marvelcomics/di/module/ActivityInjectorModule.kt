package com.marchenaya.marvelcomics.di.module

import com.marchenaya.marvelcomics.di.annotation.PerActivity
import com.marchenaya.marvelcomics.di.module.activity.MainActivityModule
import com.marchenaya.marvelcomics.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityInjectorModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivityInjector(): MainActivity
}
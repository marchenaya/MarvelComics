package com.marchenaya.marvelcomics.di.module.activity

import androidx.navigation.findNavController
import com.marchenaya.marvelcomics.di.annotation.PerActivity
import com.marchenaya.marvelcomics.ui.MainActivity
import com.marchenaya.marvelcomics.ui.comicList.ComicListFragment
import com.marchenaya.presentation.di.annotation.PerFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ComicListFragmentModule::class])
class MainActivityModule {

    @PerActivity
    @Provides
    fun mainNavController(mainActivity: MainActivity) =
        mainActivity.findNavController(mainActivity.getNavControllerId())

}

@Module
private interface ComicListFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    fun comicListFragmentInjector(): ComicListFragment
}
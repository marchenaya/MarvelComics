package com.marchenaya.marvelcomics.di.module.activity

import androidx.navigation.findNavController
import com.marchenaya.marvelcomics.di.annotation.PerActivity
import com.marchenaya.marvelcomics.di.annotation.PerFragment
import com.marchenaya.marvelcomics.ui.comicDetail.ComicDetailFragment
import com.marchenaya.marvelcomics.ui.comicList.ComicListFragment
import com.marchenaya.marvelcomics.ui.comicList.ComicListFragmentNavigatorListener
import com.marchenaya.marvelcomics.ui.main.MainActivity
import com.marchenaya.marvelcomics.ui.main.MainNavigator
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ComicListFragmentModule::class, ComicDetailFragmentModule::class])
class MainActivityModule {

    @PerActivity
    @Provides
    fun mainNavController(mainActivity: MainActivity) =
        mainActivity.findNavController(mainActivity.getNavControllerId())

    @PerActivity
    @Provides
    fun foodListFragmentNavigatorListener(mainNavigator: MainNavigator)
            : ComicListFragmentNavigatorListener = mainNavigator

}

@Module
private interface ComicListFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    fun comicListFragmentInjector(): ComicListFragment
}

@Module
private interface ComicDetailFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    fun comicDetailFragmentInjector(): ComicDetailFragment
}
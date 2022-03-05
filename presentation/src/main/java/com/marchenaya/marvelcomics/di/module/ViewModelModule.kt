package com.marchenaya.marvelcomics.di.module

import androidx.lifecycle.ViewModel
import com.marchenaya.marvelcomics.ui.comicDetail.ComicDetailFragmentViewModel
import com.marchenaya.marvelcomics.ui.comicList.ComicListFragmentViewModel
import com.marchenaya.presentation.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ComicListFragmentViewModel::class)
    fun bindComicListFragmentViewModel(viewModel: ComicListFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ComicDetailFragmentViewModel::class)
    fun bindComicDetailFragmentViewModel(viewModel: ComicDetailFragmentViewModel): ViewModel

}

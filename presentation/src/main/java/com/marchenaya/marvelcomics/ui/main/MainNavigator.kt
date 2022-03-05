package com.marchenaya.marvelcomics.ui.main

import androidx.navigation.NavController
import com.marchenaya.marvelcomics.component.navigation.NavigationComponent
import com.marchenaya.marvelcomics.di.annotation.PerActivity
import com.marchenaya.marvelcomics.ui.comicList.ComicListFragmentDirections
import com.marchenaya.marvelcomics.ui.comicList.ComicListFragmentNavigatorListener
import dagger.Lazy
import javax.inject.Inject

@PerActivity
class MainNavigator @Inject constructor(
    private val navController: Lazy<NavController>,
    private val navigationComponent: NavigationComponent
) : ComicListFragmentNavigatorListener {

    override fun displayFoodDetailFragment(comicId: Int, comicTitle: String) {
        if (navigationComponent.isNavigationEventBlocked(navController.get())) {
            return
        }
        navController.get()
            .navigate(
                ComicListFragmentDirections.actionComicListFragmentToComicDetailFragment(
                    comicId, comicTitle
                )
            )
    }
}
package com.marchenaya.marvelcomics.component.navigation

import androidx.navigation.NavController

interface NavigationComponent {

    fun isNavigationEventBlocked(
        navController: NavController,
        expectedDestinationId: Int? = null
    ): Boolean

}

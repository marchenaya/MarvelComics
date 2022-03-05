package com.marchenaya.marvelcomics.component.navigation

import androidx.navigation.NavController
import javax.inject.Inject

const val NAVIGATION_EVENT_DELAY = 300L

class NavigationComponentImpl @Inject constructor() : NavigationComponent {

    private var lastNavigationEventTimestamp: Long = 0

    override fun isNavigationEventBlocked(
        navController: NavController,
        expectedDestinationId: Int?
    ): Boolean =
        if (System.currentTimeMillis() - lastNavigationEventTimestamp < NAVIGATION_EVENT_DELAY
            || expectedDestinationId != null && navController.currentDestination?.id != expectedDestinationId
        ) {
            true
        } else {
            lastNavigationEventTimestamp = System.currentTimeMillis()
            false
        }

}
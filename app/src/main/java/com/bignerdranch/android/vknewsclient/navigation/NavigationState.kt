package com.bignerdranch.android.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(
                Screen.NewsFeed.route
            ) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberNavigateState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return NavigationState(navHostController)
}
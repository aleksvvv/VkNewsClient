package com.bignerdranch.android.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.ROUTE_NEWS_FEED,
    ) {

        composable(Screen.ROUTE_NEWS_FEED) {
            homeScreenContent()
        }
        composable(Screen.ROUTE_FAVORITE) {
            favoriteScreenContent()
        }
        composable(Screen.ROUTE_PROFILE) {
            profileScreenContent()
        }
    }
}
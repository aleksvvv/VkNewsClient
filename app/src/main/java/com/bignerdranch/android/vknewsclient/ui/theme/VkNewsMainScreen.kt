package com.bignerdranch.android.vknewsclient.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.bignerdranch.android.vknewsclient.MainViewModel
import com.bignerdranch.android.vknewsclient.navigation.AppNavGraph
import com.bignerdranch.android.vknewsclient.navigation.NavigationState
import com.bignerdranch.android.vknewsclient.navigation.Screen
import com.bignerdranch.android.vknewsclient.navigation.rememberNavigateState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val selectedNavItem by viewModel.selectedNavItem.observeAsState(NavigationItem.Home)
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    val floatingState = remember {
        mutableStateOf(true)
    }

    val navigationState = rememberNavigateState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            if (floatingState.value) {
                FloatingActionButton(onClick = {

                    scope.launch {
                        val action = snackbarHostState.showSnackbar(
                            message = "Hello World",
                            actionLabel = "Close",
                            duration = SnackbarDuration.Long
                        )
                        if (action == SnackbarResult.ActionPerformed) {
                            floatingState.value = false
                        }
                    }
                }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry = navigationState.navHostController.currentBackStackEntry
                val items =
                    arrayOf(
                        NavigationItem.Home,
                        NavigationItem.Favorite,
                        NavigationItem.Profile
                    )
                items.forEach { item ->
                    BottomNavigationItem(
                        selected = navBackStackEntry?.destination?.route == item.screen.route,
                        onClick = {
                                  navigationState.navigateTo(item.screen.route)
//                            navHostController.navigate(item.screen.route) {
//                                popUpTo(
//                                   Screen.NewsFeed.route
//                                ){
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) {

        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = { HomeScreen(viewModel = viewModel) },
            favoriteScreenContent = { Text(text = "Favorite") },
            profileScreenContent = { Text(text = "Profile") }
        )

//        when(selectedNavItem){
//            NavigationItem.Home-> HomeScreen(viewModel = viewModel)
//            NavigationItem.Profile->{
//                Text(text = "Profile")}
//            NavigationItem.Favorite->{Text(text = "Favorite")}
//        }

    }
}
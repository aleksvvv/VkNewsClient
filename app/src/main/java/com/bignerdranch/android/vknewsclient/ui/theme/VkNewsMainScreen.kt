package com.bignerdranch.android.vknewsclient.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    val floatingState = remember {
        mutableStateOf(true)
    }
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
                val selectedItemPosition = remember {
                    mutableStateOf(0)
                }

                val items =
                    arrayOf(
                        NavigationItem.Home,
                        NavigationItem.Favorite,
                        NavigationItem.Profile
                    )
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectedItemPosition.value == index,
                        onClick = {
                            selectedItemPosition.value = index
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

    }
}
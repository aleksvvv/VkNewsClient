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
import com.bignerdranch.android.vknewsclient.MainViewModel
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
                val items =
                    arrayOf(
                        NavigationItem.Home,
                        NavigationItem.Favorite,
                        NavigationItem.Profile
                    )
                items.forEach{  item ->
                    BottomNavigationItem(
                        selected = viewModel.selectedNavItem.value == item,
                        onClick = {
                            viewModel.stateScreen(item)
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

        when(selectedNavItem){
            NavigationItem.Home-> HomeScreen(viewModel = viewModel)
            NavigationItem.Profile->{
                Text(text = "Profile")}
            NavigationItem.Favorite->{Text(text = "Favorite")}
        }

//        val feedPosts =
//            viewModel.feedPosts.observeAsState(listOf())
//
//        LazyColumn(
//            contentPadding = PaddingValues(
//                top = 16.dp,
//                start = 8.dp,
//                end = 8.dp,
//                bottom = 72.dp
//            ),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//
//            items(
//                items = feedPosts.value,
//                key = { it.id }
//            ) { feedPost ->
//                val stateDismiss = rememberDismissState()
//                if (stateDismiss.isDismissed(DismissDirection.EndToStart)){
//                    viewModel.remove(feedPost)
//                }
//                SwipeToDismiss(
//                    modifier = Modifier.animateItemPlacement(),
//                    state = stateDismiss,
//                    directions = setOf(DismissDirection.EndToStart),
//                    background = {
//                        Box(
//                            modifier = Modifier.padding(12.dp)
//                                .fillMaxSize(),
//                            contentAlignment = Alignment.CenterEnd) {
//                            Text(text = "Delete Item",
//                                fontSize = 24.sp)
//                        }
//
//                    }) {
//                    PostVcCard(
//                        feedPost = feedPost,
//                        onViewClickListener = { statisticItem ->
//                            viewModel.updateCount(feedPost, statisticItem)
//                        },
//                        onSharesClickListener = { statisticItem ->
//                            viewModel.updateCount(feedPost, statisticItem)
//                        },
//                        onCommentsClickListener = { statisticItem ->
//                            viewModel.updateCount(feedPost, statisticItem)
//                        },
//                        onLikesClickListener = { statisticItem ->
//                            viewModel.updateCount(feedPost, statisticItem)
//                        }
//                    )
//                }
//
//            }
//        }


    }
}
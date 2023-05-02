package com.bignerdranch.android.vknewsclient.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.vknewsclient.MainViewModel
import com.bignerdranch.android.vknewsclient.domain.FeedPost
import com.bignerdranch.android.vknewsclient.domain.PostComment

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
) {

    val screenState =
        viewModel.screenState.observeAsState(HomeScreenState.Initial)

    val currentState = screenState.value

    when (currentState) {
        is HomeScreenState.Posts -> {
            FeedPosts(viewModel = viewModel, posts = currentState.posts)
        }

        is HomeScreenState.Comments -> {
            CommentsScreen(
                feedPost = currentState.feedPost,
                comments = currentState.comments,
                onBackPressed = {
                    viewModel.closeComments()
                }
            )
            BackHandler() {
                viewModel.closeComments()
            }
        }

        is HomeScreenState.Initial -> {}
    }

//    if (feedPosts.value.isNotEmpty()) {
//        val comments = mutableListOf<PostComment>().apply {
//            repeat(20) {
//                add(PostComment(id = it))
//            }
//        }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPosts(
    viewModel: MainViewModel,
    posts: List<FeedPost>
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 72.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(
            items = posts,
            key = { it.id }
        ) { feedPost ->
            val stateDismiss = rememberDismissState()
            if (stateDismiss.isDismissed(DismissDirection.EndToStart)) {
                viewModel.remove(feedPost)
            }
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = stateDismiss,
                directions = setOf(DismissDirection.EndToStart),
                background = {
                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "Delete Item",
                            fontSize = 24.sp
                        )
                    }

                }) {
                PostVcCard(
                    feedPost = feedPost,
                    onViewClickListener = { statisticItem ->
                        viewModel.updateCount(feedPost, statisticItem)
                    },
                    onSharesClickListener = { statisticItem ->
                        viewModel.updateCount(feedPost, statisticItem)
                    },
                    onCommentsClickListener = {
                        viewModel.showComments(feedPost = feedPost)
                    },
                    onLikesClickListener = { statisticItem ->
                        viewModel.updateCount(feedPost, statisticItem)
                    }
                )
            }

        }
    }
}
package com.bignerdranch.android.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.vknewsclient.domain.FeedPost
import com.bignerdranch.android.vknewsclient.domain.PostComment
import com.bignerdranch.android.vknewsclient.domain.StatisticItem
import com.bignerdranch.android.vknewsclient.ui.theme.HomeScreenState
import com.bignerdranch.android.vknewsclient.ui.theme.NavigationItem

class MainViewModel : ViewModel() {

    val comments = mutableListOf<PostComment>().apply {
        repeat(10) {
            add(PostComment(id = it))
        }
    }
    val initFeed = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }
    val initialState = HomeScreenState.Posts(initFeed)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private val _selectedNavItem = MutableLiveData<NavigationItem>(NavigationItem.Home)
    val selectedNavItem: LiveData<NavigationItem> = _selectedNavItem

    fun stateScreen(item: NavigationItem) {
        _selectedNavItem.value = item
    }

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()

        val oldStatistics = feedPost.statPost

        val newStatistics = oldStatistics
            .toMutableList().apply {
                replaceAll { oldItem ->
                    if (oldItem.type == item.type) {
                        oldItem.copy(count = oldItem.count + 1)
                    } else {
                        oldItem
                    }
                }
            }
        val newFeedPost = feedPost.copy(statPost = newStatistics)
        val newPosts = oldPosts.toMutableList().apply {
            replaceAll {
                if (newFeedPost.id == it.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = HomeScreenState.Posts(posts = newPosts)
    }

    private var savedState: HomeScreenState? = initialState
    fun showComments(feedPost: FeedPost) {
        savedState = _screenState.value
        _screenState.value = HomeScreenState.Comments(feedPost, comments)
    }

    fun closeComments() {
        _screenState.value = savedState
    }

    fun remove(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = HomeScreenState.Posts(posts = oldPosts)
    }

}
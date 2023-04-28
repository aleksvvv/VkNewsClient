package com.bignerdranch.android.vknewsclient

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.vknewsclient.domain.FeedPost
import com.bignerdranch.android.vknewsclient.domain.StatisticItem

class MainViewModel : ViewModel() {
    val initFeed = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val _feedPosts = MutableLiveData<List<FeedPost>>(initFeed)
    val feedPosts: LiveData<List<FeedPost>> = _feedPosts

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()

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
        _feedPosts.value = oldPosts.toMutableList().apply {
            replaceAll {
                if (newFeedPost.id == it.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
    }
    fun remove(feedPost: FeedPost){
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        oldPosts.remove(feedPost)
        _feedPosts.value = oldPosts
    }

}
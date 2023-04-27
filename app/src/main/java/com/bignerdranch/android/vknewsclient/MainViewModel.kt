package com.bignerdranch.android.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.vknewsclient.domain.FeedPost
import com.bignerdranch.android.vknewsclient.domain.StatisticItem

class MainViewModel: ViewModel() {

    private val _feedPost = MutableLiveData<FeedPost>(FeedPost())
    val feedPost:LiveData<FeedPost> = _feedPost

    fun updateCount(item: StatisticItem){
        val oldStatisticsItem =
            feedPost.value?.statPost ?: throw IllegalStateException()

        val newStatisticsItem = oldStatisticsItem
            .toMutableList().apply {
                replaceAll { oldItem ->
                    if (oldItem.type == item.type) {
                        oldItem.copy(count = oldItem.count + 1)
                    } else {
                        oldItem
                    }
                }
            }
        _feedPost.value = feedPost.value?.copy(statPost = newStatisticsItem)
    }

}
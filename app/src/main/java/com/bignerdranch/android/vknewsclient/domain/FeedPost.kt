package com.bignerdranch.android.vknewsclient.domain

import androidx.lifecycle.LiveData
import com.bignerdranch.android.vknewsclient.R

data class FeedPost (

    val communityName:String = "Уволено",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val textPost: String = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
    val imagePost: Int = R.drawable.post_content_image,
    val statPost: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEWS,200),
        StatisticItem(StatisticType.COMMENTS,123),
        StatisticItem(StatisticType.SHARES, 11),
        StatisticItem(StatisticType.LIKES, 90)
    )
        )
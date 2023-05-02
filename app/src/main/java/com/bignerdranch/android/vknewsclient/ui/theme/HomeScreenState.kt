package com.bignerdranch.android.vknewsclient.ui.theme

import com.bignerdranch.android.vknewsclient.domain.FeedPost
import com.bignerdranch.android.vknewsclient.domain.PostComment

sealed class HomeScreenState{

    object Initial: HomeScreenState()

    data class Posts(val posts: List<FeedPost>):HomeScreenState()

    data class Comments(
        val feedPost:FeedPost,
        val comments: List<PostComment>
        ):HomeScreenState()
}

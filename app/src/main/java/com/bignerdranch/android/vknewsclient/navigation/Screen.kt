package com.bignerdranch.android.vknewsclient.navigation

sealed class Screen(
    val route: String
) {
    object NewsFeed: Screen(ROUTE_NEWS_FEED)
    object Favorite: Screen(ROUTE_FAVORITE)
    object Profile: Screen(ROUTE_PROFILE)

    companion object{
        const val ROUTE_NEWS_FEED = "route_news_feed"
        const val ROUTE_FAVORITE = "route_favorite"
        const val ROUTE_PROFILE = "route_profile"
    }
}

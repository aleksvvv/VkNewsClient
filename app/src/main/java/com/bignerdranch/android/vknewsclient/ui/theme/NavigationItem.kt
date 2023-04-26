package com.bignerdranch.android.vknewsclient.ui.theme

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.bignerdranch.android.vknewsclient.R

sealed class NavigationItem(
    val titleResId: Int,
    val icon: ImageVector
) {

    object Home: NavigationItem(
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home
    )
    object Favorite:NavigationItem(
        titleResId = R.string.navigation_item_favourite,
        icon = Icons.Outlined.Favorite
    )
    object Profile:NavigationItem(
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )

}
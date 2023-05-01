package com.bignerdranch.android.vknewsclient.domain

import com.bignerdranch.android.vknewsclient.R

data class PostComment(
    val id:Int,
    val authorName:String = "Author",
    val authorAvatarId:Int = R.drawable.comment_author_avatar,
    val commentText:String ="Long text comments.",
    val publicationDate:String = "14:00"
)

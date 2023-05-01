package com.bignerdranch.android.vknewsclient.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.vknewsclient.domain.FeedPost
import com.bignerdranch.android.vknewsclient.domain.PostComment

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CommentsScreen(
    feedPost: FeedPost,
    comments: List<PostComment>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Comments is feedPost ${feedPost.id}")
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }

    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(start =4.dp,end=4.dp,
            bottom = 72.dp, top = 8.dp)
        ) {
            items(
                items = comments,
                key = { it.id }
            ) {comment ->
                commentItem(comment = comment)
            }

        }
    }
}

@Composable
fun commentItem(comment: PostComment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp
            )
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = comment.authorAvatarId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            Text(
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary,
                text = "${comment.authorName} CommentId: ${comment.id}",
            )
            Text(
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary,
                text = comment.commentText
            )
            Text(
                fontSize = 12.sp,
                color = MaterialTheme.colors.onSecondary,
                text = comment.publicationDate
            )
        }
    }
}

@Preview
@Composable
fun test() {
    VkNewsClientTheme() {
        commentItem(comment = PostComment(id = 0))
    }

}
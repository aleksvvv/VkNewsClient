package com.bignerdranch.android.vknewsclient.ui.theme

import android.view.View.OnClickListener
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bignerdranch.android.vknewsclient.R
import com.bignerdranch.android.vknewsclient.domain.FeedPost
import com.bignerdranch.android.vknewsclient.domain.StatisticItem
import com.bignerdranch.android.vknewsclient.domain.StatisticType

@Composable
fun PostVcCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onStatisticsClickListener: (StatisticItem) -> Unit
) {
       Card(modifier = modifier) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            PostHeader(feedPost)
            Text(text = feedPost.textPost)
            Spacer(modifier = Modifier.height(6.dp))
            Image(
                painter = painterResource(id = feedPost.imagePost),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            StatisticPost(
                statistics = feedPost.statPost,
                onItemClickListener = {
                    onStatisticsClickListener(it)
                }
            )
        }
    }
}

@Composable
fun StatisticPost(
    statistics: List<StatisticItem>,
    onItemClickListener: (StatisticItem) -> Unit
) {
    val feedPost = FeedPost()
    Row() {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            val viewItems = statistics.getItemByType(StatisticType.VIEWS)
            TwoElement(
                text = viewItems.count.toString(),
                painter = painterResource(id = R.drawable.ic_views_count),
                onItemClickListener = {
                    onItemClickListener(viewItems)
                }
                            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val commentsItems = statistics.getItemByType(StatisticType.COMMENTS)
            TwoElement(
                text = commentsItems.count.toString(),
                painter = painterResource(id = R.drawable.ic_share),
                onItemClickListener = {
                    onItemClickListener(commentsItems)
                }
            )
            val sharesItems = statistics.getItemByType(StatisticType.SHARES)
            TwoElement(
                text = sharesItems.count.toString(),
                painter = painterResource(id = R.drawable.ic_comment),
                onItemClickListener = {
                    onItemClickListener(sharesItems)
                }
            )
            val likesItems = statistics.getItemByType(StatisticType.LIKES)
            TwoElement(
                text = likesItems.count.toString(),
                painter = painterResource(id = R.drawable.ic_like),
                onItemClickListener = {
                    onItemClickListener(likesItems)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}

@Composable
private fun TwoElement(
    text: String,
    painter: Painter,
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable { onItemClickListener()} ,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(text = text)
        Spacer(modifier = Modifier.width(4.dp))
        Image(painter = painter, contentDescription = null)
    }
}

@Composable
private fun PostHeader(
    feedPost: FeedPost
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Text(
                text = feedPost.communityName,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = feedPost.publicationDate,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "",
            tint = MaterialTheme.colors.onSecondary
        )
    }
}



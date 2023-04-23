package com.bignerdranch.android.vknewsclient.ui.theme

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

@Composable
fun PostVcCard() {
    Card() {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            PostHeader()
            Text(text = stringResource(R.string.TextPost))
            Spacer(modifier = Modifier.height(6.dp))
            Image(
                painter = painterResource(id = R.drawable.post_content_image),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(6.dp))
            StatisticPost()
        }
    }
}

@Composable
fun StatisticPost() {
    Row() {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            TwoElement(text = "200", painter = painterResource(id = R.drawable.ic_views_count))
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TwoElement(text = "200", painter = painterResource(id = R.drawable.ic_share))
            TwoElement(text = "11", painter = painterResource(id = R.drawable.ic_comment))
            TwoElement(text = "491", painter = painterResource(id = R.drawable.ic_like))
        }
    }

}

@Composable
private fun TwoElement(text: String, painter: Painter) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = text)
        Spacer(modifier = Modifier.width(4.dp))
        Image(painter = painter, contentDescription = null)
    }
}

@Composable
private fun PostHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.post_comunity_thumbnail),
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
                text = "уволено",
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "14.00",
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
@Preview
@Composable
private fun previewDark(){
    VkNewsClientTheme(darkTheme = true) {
        PostVcCard()
    }
   }
@Preview
@Composable
private fun previewLight(){
    VkNewsClientTheme(darkTheme = false) {
        PostVcCard()
    }
}
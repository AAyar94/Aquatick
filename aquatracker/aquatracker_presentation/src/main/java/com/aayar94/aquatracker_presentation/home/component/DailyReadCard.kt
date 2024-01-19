package com.aayar94.aquatracker_presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyReadCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    text: String,
    onClick: () -> Unit,
    shape: RoundedCornerShape
) {
    Card(onClick = onClick, modifier = modifier, shape = shape) {
        Box(modifier = Modifier, contentAlignment = Alignment.Center) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds, colorFilter = ColorFilter.tint(
                    Color.Black,
                    BlendMode.Darken
                )
            )

            Text(
                text = text,
                modifier = Modifier.align(Alignment.BottomCenter),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )

        }
    }
}
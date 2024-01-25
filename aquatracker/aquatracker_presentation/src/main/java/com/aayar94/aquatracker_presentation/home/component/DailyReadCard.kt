package com.aayar94.aquatracker_presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview
import com.aayar94.compose.AquatickTheme

@Composable
fun DailyReadCard(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    title: String,
    text: String,
    shape: RoundedCornerShape,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    OutlinedCard(
        modifier = modifier.clickable { onClick() },
        shape = shape,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.background
        ), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(0.7f),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                if (imageUrl != null) {
                    AsyncImage(
                        modifier = Modifier
                            .size(64.dp)
                            .weight(0.3f),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Fit,
                    )
                } else {
                    CircularProgressIndicator()
                }
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = text,
                modifier = Modifier,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 5
            )
        }
    }
}

@DevicesPreview
@Composable
fun PreviewDailyIntakeCard() {
    AquatickTheme {
        DailyReadCard(
            imageUrl = null,
            title = "selam",
            text = "lorem ipsum",
            shape = RoundedCornerShape(20.dp),
            onClick = {}
        )
    }
}
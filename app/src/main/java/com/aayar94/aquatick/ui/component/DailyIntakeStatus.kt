package com.aayar94.aquatick.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.aayar94.aquatick.R
import com.example.compose.AquatickTheme

@Composable
fun DailyIntakeStatusCard(
    modifier: Modifier = Modifier,
    lastIntakeTime: String,
    lastIntakeAmount: String,
    buttonOnclick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(
                alpha = 0.85f
            )
        ), border = BorderStroke(width = 1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = lastIntakeTime,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = lastIntakeAmount,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            FilledTonalButton(
                onClick = { buttonOnclick() },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 12.dp),
                colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.inversePrimary)
            ) {
                Text(
                    text = stringResource(R.string.add_a_goal),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Image(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .zIndex(1f),
                painter = painterResource(id = R.drawable.water_drop_blue),
                contentDescription = null, contentScale = ContentScale.Fit
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .align(Alignment.BottomCenter)
                    .zIndex(1f),
                painter = painterResource(id = R.drawable.onboaring_shape1),
                contentDescription = null,
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .zIndex(2f),
                painter = painterResource(id = R.drawable.onboarding_shape2),
                contentDescription = null,
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDailyIntakeStatusCard() {
    AquatickTheme {
        DailyIntakeStatusCard(
            lastIntakeTime = "08:00",
            lastIntakeAmount = "200 ml",
            buttonOnclick = {})
    }
}
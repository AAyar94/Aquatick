package com.aayar94.aquatracker_presentation.home.component

import android.app.ActivityManager.AppTask
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core.R.drawable as AppDrawable

@Composable
fun DailyGoalCard(
    modifier: Modifier = Modifier,
    currentIntake: String,
    dailyIntakeAmount: String,
    onDrinkClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    ElevatedCard(
        modifier = modifier, shape = shapes.largeCornerRadius, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.4f
            )
        ), elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterStart),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your current intake is: $currentIntake",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Your daily goal is: $dailyIntakeAmount",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Image(
                painter = painterResource(id = AppDrawable.onboaring_shape1),
                contentDescription = "onboarding_shape0",
                modifier = Modifier.align(
                    Alignment.BottomCenter
                ),
                contentScale = ContentScale.Fit
            )
            Image(
                painter = painterResource(id = AppDrawable.onboarding_shape2),
                contentDescription = "onboarding_shape2",
                modifier = Modifier.align(
                    Alignment.BottomCenter
                ),
                contentScale = ContentScale.Fit
            )
            Image(
                painter = painterResource(id = com.aayar94.core.R.drawable.water_drop_white),
                contentDescription = "water_drop_icon",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterEnd),
                contentScale = ContentScale.Fit
            )
            FilledTonalButton(
                onClick = { onDrinkClick() },
                shape = shapes.largeCornerRadius,
                modifier = Modifier
                    .padding(spacing.spaceMedium)
                    .align(
                        Alignment.BottomStart
                    )
            ) {
                Text(
                    text = stringResource(id = com.aayar94.core.R.string.add_a_goal),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}
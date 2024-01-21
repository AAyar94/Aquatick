package com.aayar94.aquatracker_presentation.home.component

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.aayar94.core.R.drawable as AppDrawable

@Composable
fun DailyGoalCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape,
    currentIntake: String,
    lastIntakeTime: String?,
    lastIntakeType: String?,
    onDrinkClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    OutlinedCard(
        modifier = modifier, shape = shape, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.TopStart)
                    .padding(spacing.spaceMedium),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.zIndex(3f),
                    text = "Your current intake is: $currentIntake",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    modifier = Modifier.zIndex(3f),
                    text = if (lastIntakeTime == null || lastIntakeType == null) {
                        "You didn't drink yet "
                    } else {
                        "$lastIntakeTime ($lastIntakeType)"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Image(
                painter = painterResource(id = AppDrawable.onboaring_shape1),
                contentDescription = "onboarding_shape0",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = spacing.spaceMedium)
                    .align(
                        Alignment.BottomCenter
                    )
                    .zIndex(1f),
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Image(
                painter = painterResource(id = AppDrawable.onboarding_shape2),
                contentDescription = "onboarding_shape2",
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = spacing.spaceMedium)
                    .align(
                        Alignment.BottomCenter
                    )
                    .zIndex(1f),
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )
            Image(
                painter = painterResource(id = com.aayar94.core.R.drawable.water_drop_blue),
                contentDescription = "water_drop_icon",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterEnd)
                    .zIndex(2f),
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
                    .zIndex(3f),
                colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = stringResource(id = com.aayar94.core.R.string.add_a_goal),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}
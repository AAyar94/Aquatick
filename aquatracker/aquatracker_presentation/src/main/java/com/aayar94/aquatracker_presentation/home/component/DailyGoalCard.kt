package com.aayar94.aquatracker_presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.aayar94.core.domain.model.Gender
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core.R.drawable as AppDrawable
import com.aayar94.core.R.string as AppText

@Composable
fun DailyGoalCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape,
    currentIntake: String,
    lastIntakeTime: String?,
    lastIntakeType: String?,
    gender: Gender,
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
                if (currentIntake.isNotBlank()) {
                    Text(
                        modifier = Modifier.zIndex(3f),
                        text = stringResource(
                            id = com.aayar94.core.R.string.your_current_intake_is,
                            currentIntake
                        ) + stringResource(id = AppText.ml),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    modifier = Modifier.zIndex(3f),
                    text = if (lastIntakeTime == null || lastIntakeType == null) {
                        stringResource(id = AppText.you_didnt_drink_yet)
                    } else {
                        stringResource(
                            id = AppText.your_last_intake_is,
                            lastIntakeType.replace("_", " "),
                            lastIntakeTime.toString()
                        )
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
                contentScale = ContentScale.FillWidth,
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
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )
            Image(
                painter = when (gender) {
                    Gender.Male -> {
                        painterResource(id = AppDrawable.man_drinks_water)
                    }
                    Gender.Female -> {
                        painterResource(id = AppDrawable.woman_drink_water)
                    }
                    else -> painterResource(id = AppDrawable.water_intake_card_image)
                },
                contentDescription = "water_drop_icon",
                modifier = Modifier
                    .size(150.dp)
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
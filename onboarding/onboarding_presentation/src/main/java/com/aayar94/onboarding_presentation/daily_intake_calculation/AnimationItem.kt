package com.aayar94.onboarding_presentation.daily_intake_calculation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun CalculationAnimationItem(
    string: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val spacing = LocalSpacing.current
        Checkbox(checked = true, onCheckedChange = { }, enabled = true)
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Text(
            text = "Saved $string",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
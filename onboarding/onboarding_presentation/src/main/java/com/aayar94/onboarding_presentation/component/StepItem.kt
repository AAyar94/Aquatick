package com.aayar94.onboarding_presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepItem(
    text: String,
    tint: Color
) {
    Row {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null, tint = tint
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = tint, fontSize = 16.sp)
    }
}


@Preview
@Composable
fun PreviewStepItem() {
    StepItem("Step One", MaterialTheme.colorScheme.primary)
}
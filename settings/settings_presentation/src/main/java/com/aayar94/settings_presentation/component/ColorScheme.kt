package com.aayar94.settings_presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aayar94.core_ui.theme.AquatickTheme
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.theme.color_schemes.BlueColorScheme
import com.aayar94.core_ui.theme.color_schemes.GreenColorScheme
import com.aayar94.core_ui.theme.color_schemes.RedColorScheme
import com.aayar94.core_ui.theme.color_schemes.YellowColorScheme
import com.aayar94.core_ui.util.DevicesPreview

@Composable
fun ColorScheme(
    modifier: Modifier = Modifier,
    color: Color,
    colorSchemeName: String,
    isSelected: Boolean,
    onClick: (String) -> Unit
) {
    val spacing = LocalSpacing.current
    Box(modifier = modifier
        .clip(RoundedCornerShape(100.dp))
        .clickable { onClick(colorSchemeName) }) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(color)
                .padding(spacing.spaceMedium)
        )
        if (isSelected) {
            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                imageVector = Icons.Outlined.Check,
                contentDescription = null, tint = Color.Black
            )
        }

    }
}

@DevicesPreview
@Composable
fun PreviewColorScheme() {
    AquatickTheme {
        Row {
            ColorScheme(
                color = GreenColorScheme, isSelected = false, colorSchemeName = "Blue",
                onClick = {}
            )
            ColorScheme(
                color = BlueColorScheme,
                isSelected = true,
                colorSchemeName = "Red",
                onClick = {})
            ColorScheme(
                color = RedColorScheme,
                isSelected = false,
                colorSchemeName = "Yellow",
                onClick = {})
            ColorScheme(
                color = YellowColorScheme,
                isSelected = false,
                onClick = {},
                colorSchemeName = "Blue"
            )

        }
    }
}
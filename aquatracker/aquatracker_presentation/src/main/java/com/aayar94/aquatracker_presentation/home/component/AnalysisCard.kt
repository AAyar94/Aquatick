package com.aayar94.aquatracker_presentation.home.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.aayar94.core_ui.theme.AquatickTheme
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview

@Composable
fun AnalysisCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    contentColor: Color
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        shape = shapes.largeCornerRadius
    ) {
        Icon(imageVector = Icons.Outlined.History, contentDescription = null)
        /*Image(
            painter = painterResource(id = com.aayar94.core.R.drawable.analisist_icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(contentColor)
        )*/
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Text(
            text = stringResource(id = com.aayar94.core.R.string.drink_history),
            color = contentColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@DevicesPreview
@Composable
fun PreviewAnalysisCard() {
    AquatickTheme {
        AnalysisCard(onClick = { }, contentColor = MaterialTheme.colorScheme.onPrimaryContainer)
    }

}
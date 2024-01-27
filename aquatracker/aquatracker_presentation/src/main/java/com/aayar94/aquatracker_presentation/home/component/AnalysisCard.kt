package com.aayar94.aquatracker_presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.DevicesPreview
import kotlin.reflect.KFunction0

@Composable
fun AnalysisCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        shape = shapes.largeCornerRadius
    ) {
        Image(
            painter = painterResource(id = com.aayar94.core.R.drawable.analisist_icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Text(text = stringResource(id = com.aayar94.core.R.string.analysis))
    }
}


@DevicesPreview
@Composable
fun PreviewAnalysisCard() {
    AnalysisCard(onClick = { })
}
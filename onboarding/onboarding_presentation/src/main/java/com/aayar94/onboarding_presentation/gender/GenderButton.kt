package com.aayar94.onboarding_presentation.gender

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun GenderButton(
    modifier: Modifier = Modifier,
    genderIcon: Int,
    genderDescription: String,
    isSelected: Boolean,
    selectionChange: () -> Unit
) {
    val spacing = LocalSpacing.current
    Box(modifier = modifier.clickable { selectionChange() }, contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = genderIcon),
                contentDescription = genderDescription,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
            RadioButton(selected = isSelected, onClick = selectionChange)
        }
    }
}
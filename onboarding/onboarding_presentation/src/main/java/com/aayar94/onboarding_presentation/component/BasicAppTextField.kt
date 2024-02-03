package com.aayar94.onboarding_presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun BasicAppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    unit: String? = null,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.onBackground, fontSize = 70.sp
    ),
    keyboardType: KeyboardType
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            singleLine = true,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .alignBy(LastBaseline)
        )
        if (!unit.isNullOrBlank()) {
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            Text(
                text = unit, modifier = Modifier.alignBy(LastBaseline)
            )
        }
    }
}
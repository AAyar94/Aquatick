package com.aayar94.onboarding_presentation.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = keyboardOptions
    )
}
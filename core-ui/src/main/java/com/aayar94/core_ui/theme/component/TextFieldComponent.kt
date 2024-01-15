package com.aayar94.core_ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    label: String,
    hint: String,
    keyboardOptions: KeyboardOptions,
    textColor: androidx.compose.ui.graphics.Color,
    text: String,
    onTextChange: (str: String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = label,
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp, start = 8.dp),
            color = textColor
        )
        TextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer),
            placeholder = {
                Text(
                    text = hint, color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = keyboardOptions
        )
    }
}


@Preview
@Composable
fun PreviewTextFieldComponent() {
    Column(modifier = Modifier.fillMaxSize()) {
        TextFieldComponent(
            label = "Name",
            hint = "Name",
            keyboardOptions = KeyboardOptions.Default,
            textColor = MaterialTheme.colorScheme.onBackground,
            text = "Adem"
        ) {}
    }

}
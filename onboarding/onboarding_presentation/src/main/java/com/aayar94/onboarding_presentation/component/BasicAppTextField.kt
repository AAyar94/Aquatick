package com.aayar94.onboarding_presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.aayar94.core_ui.theme.AquatickTheme
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun BasicAppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    unit: String? = null,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 48.sp,
        textAlign = TextAlign.Center
    ),
    keyboardType: KeyboardType
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        /*var textFieldWidth by remember {
            mutableStateOf(0.dp)
        }
        TODO CHANGE BASIC TEXT FIELD TO TEXT FIELD
         */
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(),
            textStyle = textStyle.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            placeholder = { Text(text = placeholder) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.primary
            )
        )
        /* BasicTextField(
             value = value,
             onValueChange = onValueChange,
             textStyle = textStyle,
             keyboardOptions = KeyboardOptions(
                 keyboardType = keyboardType
             ),
             singleLine = true,
             modifier = Modifier
                 .weight(1f)
                 .wrapContentWidth()
             //.onGloballyPositioned { textFieldWidth = it.size.width.dp },
         )*/
        if (!unit.isNullOrBlank()) {
            Spacer(
                modifier = Modifier
                    .width(spacing.spaceSmall)
                //.padding(start = textFieldWidth.intValue.dp)
            )
            Text(
                text = unit, modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(),
                //.padding(start = textFieldWidth),
                //style = textStyle
                style = textStyle.copy(textAlign = TextAlign.Start, fontSize = 24.sp)
            )
        }
    }
}


@PreviewDynamicColors
@PreviewLightDark
@Composable
fun PreviewTextField() {
    AquatickTheme {
        Column {
            BasicAppTextField(
                modifier = Modifier.wrapContentSize(),
                value = "Adem",
                onValueChange = { },
                keyboardType = KeyboardType.Text,
                placeholder = "Your Name"
            )
            BasicAppTextField(
                modifier = Modifier.wrapContentSize(),
                value = "Your Name",
                onValueChange = { },
                keyboardType = KeyboardType.Text, unit = "kg",
                placeholder = "80.0"
            )
        }
    }
}
package com.aayar94.aquatick.core.theme.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aayar94.aquatick.R

@Composable
fun RadioButtonComponent(
    modifier: Modifier = Modifier,
    selected: Boolean,
    icon: Int? = null,
    onClick: () -> Unit,
    text: String,
    textColor: Color,
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        if (icon != null) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .defaultMinSize(64.dp, 64.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = selected, onClick = { onClick() }, colors = RadioButtonDefaults.colors(
                    androidx.compose.material3.MaterialTheme.colorScheme.primary,
                    androidx.compose.material3.MaterialTheme.colorScheme.tertiary,
                    Color.Gray
                )
            )
            Text(
                text = text,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = modifier,
                color = textColor
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewRadioButtonComponent() {
    val genderList = listOf(
        "Male", "Female", "LGBTQ"
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RadioButtonComponent(
            selected = true,
            onClick = { /*TODO*/ },
            text = genderList[0],
            textColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            icon = R.drawable.gender_male
        )
        RadioButtonComponent(
            selected = false,
            onClick = { /*TODO*/ },
            text = genderList[1],
            textColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            icon = R.drawable.gender_female
        )
        RadioButtonComponent(
            selected = false,
            onClick = { /*TODO*/ },
            text = genderList[2],
            textColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            icon = R.drawable.gender_lgbtq
        )
    }
}
package com.aayar94.aquatracker_presentation.analysis.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_domain.usecase.ScreenDrinkItem
import com.aayar94.core.R
import com.aayar94.core_ui.theme.AquatickTheme
import com.aayar94.core_ui.util.DevicesPreview
import java.time.LocalDateTime

@Composable
fun LastDrinksCard(
    modifier: Modifier = Modifier,
    item: ScreenDrinkItem
) {
    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = item.drinkIcon),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = item.drinkType.name.replace("_", " "))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = item.defaultAmount.toString() + " ml")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${item.localDate.dayOfMonth}" + " / " + "${item.localDate.monthValue}" + " / " + "${item.localDate.year}")
            }
        }
    }
}

@DevicesPreview
@Composable
fun PreviewLastDrinksCard() {
    AquatickTheme {
        LastDrinksCard(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),

            ScreenDrinkItem(
                DrinkType.Tea, 100, R.drawable.ic_drink_tea,
                LocalDateTime.now()
            ),
        )
    }
}
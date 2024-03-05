package com.aayar94.aquatracker_presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_domain.model.ScreenDrinkItem
import com.aayar94.core_ui.theme.AquatickTheme
import java.time.LocalDateTime
import com.aayar94.core.R.drawable as AppDrawable

@Composable
fun MostUsedDrinks(
    modifier: Modifier,
    mostUsedDrinks: List<ScreenDrinkItem>,
    onItemClick: (ScreenDrinkItem) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        items(mostUsedDrinks) { screenDrinkItem ->
            MostUsedDrinkItem(onDrinkClick = { onItemClick(it) }, screenDrinkItem = screenDrinkItem)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostUsedDrinkItem(
    modifier: Modifier = Modifier,
    onDrinkClick: (ScreenDrinkItem) -> Unit,
    screenDrinkItem: ScreenDrinkItem
) {
    Card(
        modifier = modifier
            .size(width = 150.dp, height = 250.dp)
            .padding(8.dp),
        onClick = { onDrinkClick(screenDrinkItem) },
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = screenDrinkItem.drinkIcon),
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = screenDrinkItem.drinkType.name.replace("_", " "),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${screenDrinkItem.defaultAmount} ml",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@PreviewLightDark
@PreviewDynamicColors
@Composable
fun PreviewMostUsedItems() {
    AquatickTheme {
        MostUsedDrinks(
            modifier = Modifier.fillMaxWidth(),
            onItemClick = {},
            mostUsedDrinks = listOf(
                ScreenDrinkItem(
                    idNumber = 1,
                    DrinkType.Carbonated_Drinks,
                    250,
                    AppDrawable.ic_drink_carbonated_drink,
                    LocalDateTime.now()
                ),
                ScreenDrinkItem(
                    idNumber = 1,
                    DrinkType.Carbonated_Drinks,
                    250,
                    AppDrawable.ic_drink_carbonated_drink,
                    LocalDateTime.now()
                ),
                ScreenDrinkItem(
                    idNumber = 1,
                    DrinkType.Carbonated_Drinks,
                    250,
                    AppDrawable.ic_drink_carbonated_drink,
                    LocalDateTime.now()
                )
            )
        )
    }
}
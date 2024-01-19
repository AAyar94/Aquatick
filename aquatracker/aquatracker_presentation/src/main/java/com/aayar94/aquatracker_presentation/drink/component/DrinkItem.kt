package com.aayar94.aquatracker_presentation.drink.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aayar94.aquatracker_domain.ScreenDrinkItem

@Composable
fun DrinkItem(modifier: Modifier = Modifier, item: ScreenDrinkItem, shape: RoundedCornerShape) {
    Card(
        modifier = modifier,
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.drinkIcon),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Text(text = item.drinkType.name, color = MaterialTheme.colorScheme.onBackground)
        }
    }

}
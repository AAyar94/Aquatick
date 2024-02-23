package com.aayar94.aquatracker_presentation.drink.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aayar94.aquatracker_domain.model.DrinkScreenListItem
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun DrinkItem(
    modifier: Modifier = Modifier,
    item: DrinkScreenListItem,
    shape: RoundedCornerShape,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Card(
        modifier = modifier
            .shadow(elevation = 5.dp, shape = shape)
            .clickable { onClick() },
        shape = shape,
        colors = CardDefaults
            .cardColors(MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(width = 1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(spacing.spaceSmall),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.drinkIcon),
                contentDescription = null,
                modifier = Modifier.size(84.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = item.defaultAmount.toString() + " ml",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = item.drinkType.name.replace("_", " "),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }

}
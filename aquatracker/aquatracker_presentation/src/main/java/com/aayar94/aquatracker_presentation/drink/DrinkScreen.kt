package com.aayar94.aquatracker_presentation.drink

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.aquatracker_presentation.drink.component.DrinkItem
import com.aayar94.aquatracker_presentation.drink.component.LastDrinksCard
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun DrinkScreen(
    viewModel: DrinkViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNavigate()
                else -> Unit
            }
        }
    }
    val spacing = LocalSpacing.current
    val shape = LocalShape.current
    val listState = rememberLazyGridState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = spacing.spaceMedium),
            state = listState,
            columns = GridCells.Fixed(3)
        ) {
            items(viewModel.drinkUIState.list) { item ->
                DrinkItem(
                    modifier = Modifier.padding(spacing.spaceSmall),
                    item = item,
                    shape = shape.largeCornerRadius,
                    onClick = {
                        viewModel.onDrinkClick(item)
                        viewModel.getLastDrinks()
                    }
                )
            }
        }
        viewModel.getLastDrinks()
        Log.d("viewmodel", viewModel.lastDrinkState.size.toString())
        if (viewModel.lastDrinkState.isNotEmpty()) {
            Text(
                text = stringResource(id = com.aayar94.core.R.string.your_last_drinks),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = spacing.spaceMedium)
            )
            LazyColumn {
                items(viewModel.lastDrinkState) {
                    LastDrinksCard(item = it)
                }
            }
        }
    }
}
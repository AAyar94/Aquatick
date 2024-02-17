package com.aayar94.aquatracker_presentation.drink

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.aquatracker_domain.model.DrinkType
import com.aayar94.aquatracker_presentation.drink.component.DrinkItem
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun DrinkScreen(
    viewModel: DrinkViewModel = hiltViewModel(),
    onNavigate: () -> Unit,
    onDrinkAdd: (String) -> Unit
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
                DrinkItem(modifier = Modifier.padding(spacing.spaceSmall),
                    item = item,
                    shape = shape.largeCornerRadius,
                    onClick = {
                        viewModel.onDrinkClick(item)
                        onDrinkAdd(
                            "${item.defaultAmount} ml ${DrinkType.nameFormatter(item.drinkType.name)} added!"
                        )
                    })
            }
        }
    }
}
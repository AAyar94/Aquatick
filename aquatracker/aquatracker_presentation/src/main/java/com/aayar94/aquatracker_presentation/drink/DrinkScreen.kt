package com.aayar94.aquatracker_presentation.drink

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.aquatracker_presentation.drink.component.DrinkItem
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core_ui.util.WorkInProgress

@Composable
fun DrinkScreen(
    viewModel: DrinkViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val shape = LocalShape.current
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceMedium),
        state = listState,
        columns = GridCells.Fixed(3)
    ) {
        items(viewModel.drinkUIState.list) { item ->
            DrinkItem(
                modifier = Modifier.padding(spacing.spaceSmall),
                item = item,
                shape = shape.mediumCornerRadius
            )
        }
    }
}
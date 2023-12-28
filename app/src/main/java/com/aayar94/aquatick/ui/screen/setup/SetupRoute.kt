package com.aayar94.aquatick.ui.screen.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.R
import com.aayar94.aquatick.core.navigation.INavigationItem
import com.aayar94.aquatick.util.DevicesPreview
import com.example.compose.Indigo
import com.example.compose.Orange
import com.example.compose.Violet

fun NavGraphBuilder.setupGraph(navController: NavController) {
    composable(Setup.route) {
        val viewModel: SetupViewModel = hiltViewModel()
        SetupScreen(navController, viewModel)
    }
}

data object Setup : INavigationItem {
    override val route: String
        get() = "route_setup"

}

@Composable
fun SetupScreen(navController: NavController, viewModel: SetupViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val rainbowColors = listOf(
        Color.Red,
        Orange,
        Color.Yellow,
        Color.Green,
        Color.Blue,
        Indigo,
        Violet
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize().padding(vertical = 12.dp, horizontal = 12.dp)
                .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.select_your_age_weight_gender_and_weekly_activity_level),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@DevicesPreview
@Composable
fun PreviewSetupScreen() {
    SetupScreen(navController = rememberNavController(), viewModel = hiltViewModel())
}

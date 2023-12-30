package com.aayar94.aquatick.ui.screen.callculate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.R
import com.aayar94.aquatick.core.navigation.INavigationItem
import com.aayar94.aquatick.ui.screen.home.Home
import com.aayar94.aquatick.util.DevicesPreview
import com.example.compose.AquatickTheme

fun NavGraphBuilder.calculateGraph(navController: NavController) {
    composable(Calculate.route) {
        val viewModel: CalculateViewModel = hiltViewModel()
        CalculateScreen(navController, viewModel)
    }
}

data object Calculate : INavigationItem {
    override val route: String
        get() = "route_calculate"

}

@Composable
fun CalculateScreen(navController: NavController, viewModel: CalculateViewModel) {
    val uiState = viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.let_s_calculate_your_daily_intake),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleSmall
            )
            AnimatedVisibility(visible = uiState.value.stepOneVisibility) {
                CheckStep(text = stringResource(R.string.your_age))
            }
            AnimatedVisibility(visible = uiState.value.stepTwoVisibility) {
                CheckStep(text = stringResource(R.string.your_weight))
            }
            AnimatedVisibility(visible = uiState.value.stepThreeVisibility) {
                CheckStep(text = stringResource(R.string.your_gender))
            }
            AnimatedVisibility(visible = uiState.value.stepFourVisibility) {
                CheckStep(text = stringResource(R.string.your_daily_activity_level))
            }
            AnimatedVisibility(visible = uiState.value.stepFiveVisibility) {
                CheckStep(text = stringResource(R.string.our_special_formula))
            }

        }
        if (!uiState.value.calculatedIntakeAmount.isNullOrEmpty()) {
            Card(
                modifier = Modifier
                    .defaultMinSize(300.dp, 300.dp)
                    .fillMaxWidth(0.95f)
                    .align(Alignment.Center)
                    .zIndex(2f),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
            ) {
                Column(
                    modifier = Modifier.defaultMinSize(300.dp, 300.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.glass_of_water),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Okay your daily take is:",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = uiState.value.calculatedIntakeAmount!!,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                    FilledTonalButton(
                        onClick = { navController.navigate(Home.route) },
                        shape = MaterialTheme.shapes.large
                    ) {
                        Text(text = stringResource(id = R.string.finisg_setup))
                    }
                }
            }
        }
    }
}

@Composable
fun CheckStep(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(12.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@DevicesPreview
@Composable
fun PreviewCalculateScreen() {
    AquatickTheme {
        CalculateScreen(navController = rememberNavController(), viewModel = hiltViewModel())
    }
}
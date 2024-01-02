package com.aayar94.aquatick.ui.screen.callculate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.R
import com.aayar94.aquatick.core.navigation.INavigationItem
import com.aayar94.aquatick.ui.component.StepItem
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
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.calculateIntake()
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center
    ) {
        if (uiState.calculatedIntakeAmount.isNullOrEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.let_s_calculate_your_daily_intake),
                    color = MaterialTheme.colorScheme.onBackground
                )
                AnimatedVisibility(visible = uiState.stepOneVisibility) {
                    StepItem(
                        text = stringResource(id = R.string.your_age),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                AnimatedVisibility(visible = uiState.stepTwoVisibility) {
                    StepItem(
                        text = stringResource(id = R.string.your_weight),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                AnimatedVisibility(visible = uiState.stepThreeVisibility) {
                    StepItem(
                        text = stringResource(id = R.string.your_gender),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                AnimatedVisibility(visible = uiState.stepFourVisibility) {
                    StepItem(
                        text = stringResource(id = R.string.your_daily_activity_level),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                AnimatedVisibility(visible = uiState.stepFiveVisibility) {
                    StepItem(
                        text = stringResource(id = R.string.some_math),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                AnimatedVisibility(
                    visible = uiState.stepFiveVisibility,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(64.dp, 64.dp)
                            .align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

            }
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(200.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = "Your daily take is",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp), color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = uiState.calculatedIntakeAmount!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
                ElevatedButton(
                    onClick = { navController.navigate(Home.route) },
                    Modifier
                        .fillMaxWidth(0.5f)
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 12.dp)
                ) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    Text(text = "Go Home", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}

@DevicesPreview
@Composable
fun CalculateScreenPreview() {
    AquatickTheme {
        CalculateScreen(navController = rememberNavController(), viewModel = hiltViewModel())
    }
}
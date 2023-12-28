package com.aayar94.aquatick.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.R
import com.aayar94.aquatick.core.navigation.INavigationItem
import com.aayar94.aquatick.util.DevicesPreview
import com.example.compose.AquatickTheme

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable(Splash.route) {
        val viewModel: SplashViewModel = hiltViewModel()
        SplashScreen(navController, viewModel)
    }
}

data object Splash : INavigationItem {
    override val route: String
        get() = "route_splash"
}

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        uiState.route?.let { route ->
            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationRoute!!) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.water_drop_white),
                contentDescription = null,
                tint = Color.White,
            )
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.stay_hydrated),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.75f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Image(
            painter = painterResource(id = R.drawable.onboaring_shape1),
            contentDescription = null,
            alignment = Alignment.BottomCenter,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
        )
        Image(
            painter = painterResource(id = R.drawable.onboarding_shape2),
            contentDescription = null,
            alignment = Alignment.BottomCenter,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
        )
    }
}

@DevicesPreview
@Composable
fun PreviewSplashScreen() {
    AquatickTheme {
        SplashScreen(navController = rememberNavController(), viewModel = hiltViewModel())
    }
}
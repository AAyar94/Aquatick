package com.aayar94.aquatick.ui.screen.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.R
import com.aayar94.aquatick.core.navigation.INavigationItem
import com.aayar94.aquatick.core.theme.component.RadioButtonComponent
import com.aayar94.aquatick.core.theme.component.TextFieldComponent
import com.aayar94.aquatick.ui.screen.home.Home
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
    viewModel.getUserData()
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
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 12.dp)
                .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.select_your_age_weight_gender_and_weekly_activity_level),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            TextFieldComponent(
                label = stringResource(id = R.string.name),
                hint = stringResource(id = R.string.enter_your_name),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                textColor = MaterialTheme.colorScheme.onBackground,
                text = if (uiState.name.isNullOrBlank()) "" else uiState.name!!,
                onTextChange = {
                    viewModel.updateName(it)
                }
            )
            TextFieldComponent(
                label = stringResource(id = R.string.age),
                hint = stringResource(id = R.string.enter_your_age),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                textColor = MaterialTheme.colorScheme.onBackground,
                text = uiState.age.toString(),
                onTextChange = {
                    viewModel.updateAge(it)
                }
            )
            TextFieldComponent(
                label = stringResource(id = R.string.weight),
                hint = stringResource(id = R.string.enter_your_weight),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                textColor = MaterialTheme.colorScheme.onBackground,
                text = uiState.weight.toString(),
                onTextChange = {
                    viewModel.updateWeight(it)
                }
            )
            Text(
                text = stringResource(id = R.string.gender),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                RadioButtonComponent(
                    selected = uiState.gender == stringResource(id = R.string.male),
                    onClick = { viewModel.updateGender("Male") },
                    text = stringResource(id = R.string.male),
                    icon = R.drawable.gender_male,
                    textColor = MaterialTheme.colorScheme.onBackground
                )
                RadioButtonComponent(
                    selected = uiState.gender == stringResource(id = R.string.female),
                    onClick = { viewModel.updateGender("Female") },
                    text = stringResource(id = R.string.female),
                    icon = R.drawable.gender_female,
                    textColor = MaterialTheme.colorScheme.onBackground
                )
                RadioButtonComponent(
                    modifier = Modifier
                        .graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            val brush = Brush.horizontalGradient(rainbowColors)
                            onDrawWithContent {
                                drawContent()
                                drawRect(brush, blendMode = BlendMode.SrcAtop)
                            }
                        },
                    selected = uiState.gender == stringResource(id = R.string.lgbtq),
                    onClick = { viewModel.updateGender("LGBTQ") },
                    text = stringResource(id = R.string.lgbtq),
                    icon = R.drawable.gender_lgbtq,
                    textColor = MaterialTheme.colorScheme.onBackground
                )
            }
            //ACTIVITY LEVEL SECTION
            Text(
                text = stringResource(id = R.string.activity_level),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(4.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButtonComponent(
                    selected = uiState.activityLevel == stringResource(id = R.string.activity_level_not_active),
                    onClick = { viewModel.updateActivityLevel("Not Active") },
                    icon = R.drawable.activity_level_not_active2,
                    text = stringResource(id = R.string.activity_level_not_active),
                    textColor = MaterialTheme.colorScheme.onBackground
                )
                RadioButtonComponent(
                    selected = uiState.activityLevel == stringResource(id = R.string.activity_level_less_than_average),
                    onClick = { viewModel.updateActivityLevel("Less than average") },
                    icon = R.drawable.activity_level_less_than_average,
                    text = stringResource(id = R.string.activity_level_less_than_average),
                    textColor = MaterialTheme.colorScheme.onBackground
                )
                RadioButtonComponent(
                    selected = uiState.activityLevel == stringResource(id = R.string.activity_level_average),
                    onClick = { viewModel.updateActivityLevel("Average") },
                    icon = R.drawable.activity_level_active,
                    text = stringResource(id = R.string.activity_level_average),
                    textColor = MaterialTheme.colorScheme.onBackground
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButtonComponent(
                    selected = uiState.activityLevel == stringResource(id = R.string.activity_level_active),
                    onClick = { viewModel.updateActivityLevel("Active") },
                    icon = R.drawable.activity_level_very_active,
                    text = stringResource(id = R.string.activity_level_active),
                    textColor = MaterialTheme.colorScheme.onBackground
                )
                RadioButtonComponent(
                    selected = uiState.activityLevel == stringResource(id = R.string.activity_level_very_active),
                    icon = R.drawable.activity_level_fit,
                    onClick = { viewModel.updateActivityLevel("Very Active") },
                    text = stringResource(id = R.string.activity_level_very_active),
                    textColor = MaterialTheme.colorScheme.onBackground
                )
            }
            FilledTonalButton(
                onClick = {
                    viewModel.saveUserData()
                    navController.navigate(Home.route)
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Save")
            }
        }
    }
}


@DevicesPreview
@Composable
fun PreviewSetupScreen() {
    SetupScreen(navController = rememberNavController(), viewModel = hiltViewModel())
}

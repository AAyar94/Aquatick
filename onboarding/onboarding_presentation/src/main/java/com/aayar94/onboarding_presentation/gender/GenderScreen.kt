package com.aayar94.onboarding_presentation.gender

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.domain.model.Gender
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core.R.drawable as AppDrawable
import com.aayar94.core.R.string as AppText

@Composable
fun GenderScreen(
    onNextClick: () -> Unit,
    viewModel: GenderViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = com.aayar94.core.R.string.select_your_gender))
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                GenderButton(
                    genderIcon = AppDrawable.gender_male,
                    genderDescription = "Gender Male",
                    genderName = stringResource(id = AppText.male),
                    isSelected = viewModel.genderState is Gender.Male,
                    modifier = Modifier.size(64.dp),
                    selectionChange = { viewModel.genderChange(Gender.Male) }
                )
                GenderButton(
                    genderIcon = AppDrawable.gender_female,
                    genderDescription = "Gender Female",
                    genderName = stringResource(id = AppText.female),
                    isSelected = viewModel.genderState is Gender.Female,
                    modifier = Modifier.size(64.dp),
                    selectionChange = { viewModel.genderChange(Gender.Female) }
                )
                GenderButton(
                    genderIcon = AppDrawable.gender_lgbtq,
                    genderDescription = "Gender LGBTQ",
                    genderName = stringResource(id = AppText.lgbtq),
                    isSelected = viewModel.genderState is Gender.LGBTQ,
                    modifier = Modifier.size(64.dp),
                    selectionChange = { viewModel.genderChange(Gender.LGBTQ) }
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Button(onClick = viewModel::onNextClick) {
                Text(text = stringResource(id = AppText.next))
            }
        }
    }
}
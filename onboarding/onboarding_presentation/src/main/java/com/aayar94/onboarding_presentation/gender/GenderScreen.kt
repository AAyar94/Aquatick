package com.aayar94.onboarding_presentation.gender

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.core.domain.model.Gender
import com.aayar94.core.util.UiEvent
import com.aayar94.core_ui.theme.LocalShape
import com.aayar94.core_ui.theme.LocalSpacing
import com.aayar94.core.R.drawable as AppDrawable
import com.aayar94.core.R.string as AppText

@Composable
fun GenderScreen(
    onNextClick: () -> Unit,
    viewModel: GenderViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val shapes = LocalShape.current
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
            .background(MaterialTheme.colors.background), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = com.aayar94.core.R.string.select_your_gender),
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GenderButton(
                    genderIcon = AppDrawable.gender_male,
                    genderDescription = "Gender Male",
                    isSelected = viewModel.genderState is Gender.Male,
                    modifier = Modifier.width(64.dp).wrapContentHeight(),
                    selectionChange = { viewModel.genderChange(Gender.Male) }
                )
                GenderButton(
                    genderIcon = AppDrawable.gender_female,
                    genderDescription = "Gender Female",
                    isSelected = viewModel.genderState is Gender.Female,
                    modifier = Modifier.width(64.dp).wrapContentHeight(),
                    selectionChange = { viewModel.genderChange(Gender.Female) }
                )
                GenderButton(
                    genderIcon = AppDrawable.gender_lgbtq,
                    genderDescription = "Gender LGBTQ",
                    isSelected = viewModel.genderState is Gender.LGBTQ,
                    modifier = Modifier.width(64.dp).wrapContentHeight(),
                    selectionChange = { viewModel.genderChange(Gender.LGBTQ) }
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
            FilledTonalButton(onClick = viewModel::onNextClick, shape = shapes.mediumCornerRadius) {
                Text(text = stringResource(id = AppText.next), style = MaterialTheme.typography.h6)
            }
        }
    }
}
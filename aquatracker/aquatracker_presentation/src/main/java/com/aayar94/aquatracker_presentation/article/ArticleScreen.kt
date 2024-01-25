package com.aayar94.aquatracker_presentation.article

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aayar94.aquatracker_domain.model.Article
import com.aayar94.core_ui.theme.LocalSpacing

@Composable
fun ArticleScreen(
    articleId: Int,
    viewModel: ArticleViewModel = hiltViewModel()
) {
    viewModel.getArticle(articleId)
    val spacing = LocalSpacing.current
    val scrollState = rememberScrollState()
    val uiState = viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.value.isLoading || uiState.value.article == null) {
            CircularProgressIndicator()
        } else {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(spacing.spaceMedium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = uiState.value.article!!.Title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Text(
                    text = uiState.value.article!!.Introduction,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Column {
                    uiState.value.article!!.Points.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(text = it.PointNumber.toString())
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .clip(RoundedCornerShape(25.dp))
                                    .background(MaterialTheme.colorScheme.secondary)
                            )
                            Spacer(modifier = Modifier.width(spacing.spaceSmall))
                            Text(text = it.Point)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Text(
                    text = uiState.value.article!!.Conclusion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
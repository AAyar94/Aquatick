package com.aayar94.aquatick.ui.screen.onboarding

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.R
import com.aayar94.aquatick.core.navigation.INavigationItem
import com.aayar94.aquatick.util.DevicesPreview
import com.example.compose.AquatickTheme
import kotlinx.coroutines.launch

fun NavGraphBuilder.onboardingGraph(navController: NavController) {
    composable(Onboarding.route) {
        val viewModel: OnboardingViewModel = hiltViewModel()
        OnboardingScreen(navController, viewModel)
    }
}

data object Onboarding : INavigationItem {
    override val route: String
        get() = "route_onboarding"
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavController, viewModel: OnboardingViewModel) {
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        val item = getOnboardingDataList()
        val state = rememberPagerState {
            item.size
        }
        HorizontalPager(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) { page ->
            OnBoardingItem(item = item[page])
        }
        BottomSection(size = item.size, index = state.currentPage) {
            if (state.currentPage + 1 < item.size) {
                scope.launch {
                    state.scrollToPage(page = state.currentPage + 1)
                }
            } else {
                navController.navigate("route_setup")
                viewModel.onBoardingFinished()
            }
        }
    }
}

@Composable
fun ColumnScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }

    }
}

@Composable
fun BottomSection(size: Int, index: Int, onNextClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Indicators(size = size, index = index)
        Spacer(modifier = Modifier.height(12.dp))
        FilledTonalButton(
            onClick = { onNextClicked() },
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .wrapContentHeight()
                .padding(vertical = 12.dp), shape = MaterialTheme.shapes.large
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width = width.value)
            .clip(shape = CircleShape)
            .background(
                if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.5f
                )
            )
    ) {
    }
}

@Composable
fun OnBoardingItem(item: OnboardingDataModel) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(context, "Notification permission granted", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Notification permission declined", Toast.LENGTH_LONG).show()
        }
    }

    fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) ==
                PackageManager.PERMISSION_GRANTED
            ) {
//                Log.e(TAG, "PERMISSION_GRANTED")
                // FCM SDK (and your app) can post notifications.
            } else {
//                Log.e(TAG, "NO_PERMISSION")
                // Directly ask for the permission
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(item.backgroundColor)
            .padding(horizontal = 12.dp)
    ) {
        Image(painter = painterResource(id = item.image), contentDescription = null)
        Text(
            text = stringResource(id = item.title),
            color = item.textColor,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = item.text),
            color = item.textColor.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        if (item.isLastPage) {
            OutlinedButton(
                onClick = {
                    askNotificationPermission()
                },
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .wrapContentHeight()
                    .padding(vertical = 12.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text(text = stringResource(R.string.grand_notification_permission))
            }
        }
    }

}


@DevicesPreview
@Composable
fun PreviewOnboardingScreen() {
    AquatickTheme {
        OnboardingScreen(navController = rememberNavController(), viewModel = hiltViewModel())
    }
}
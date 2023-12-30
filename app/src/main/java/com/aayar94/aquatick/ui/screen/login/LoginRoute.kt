package com.aayar94.aquatick.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.aquatick.R
import com.aayar94.aquatick.core.navigation.INavigationItem
import com.aayar94.aquatick.core.theme.component.TextFieldComponent
import com.aayar94.aquatick.util.DevicesPreview
import com.example.compose.AquatickTheme

fun NavGraphBuilder.loginGraph(navController: NavController) {
    composable(Login.route) {
        val viewModel: LoginViewModel = hiltViewModel()
        LoginScreen(navController, viewModel)
    }
}

data object Login : INavigationItem {
    override val route: String
        get() = "route_login"
}

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    val loginClickState by remember {
        mutableStateOf(false)
    }
    val emailadreess = remember {
        mutableStateOf("adem@gmail.com")
    }
    val password = remember {
        mutableStateOf("adem@gmail.com")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.95f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (loginClickState) {
                Text(
                    text = stringResource(R.string.login_with_email),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                TextFieldComponent(
                    label = stringResource(R.string.email),
                    hint = stringResource(R.string.your_email_address_com),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    textColor = MaterialTheme.colorScheme.onBackground,
                    text = emailadreess.value,
                    onTextChange = {
                        /* TODO viewmodel email change */
                    }
                )
                TextFieldComponent(
                    label = stringResource(R.string.password),
                    hint = stringResource(R.string.enter_your_password),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    textColor = MaterialTheme.colorScheme.onBackground,
                    text = password.value,
                    onTextChange = {}
                )
                FilledTonalButton(
                    onClick = { viewModel.logInWithEmail(emailadreess.value, password.value) },
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text(text = stringResource(R.string.log_in))
                }
            } else {
                Text(
                    text = stringResource(R.string.let_s_login),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp, 24.dp),
                        imageVector = Icons.Filled.Mail,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = stringResource(id = R.string.login_with_email),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color.Red
                    ),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp, 24.dp),
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = stringResource(R.string.login_with_google),
                        color = Color.White
                    )
                }
                Text(
                    text = stringResource(R.string.you_don_t_have_an_account),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.clickable {
                        /*TODO route signup   */
                    },
                    text = stringResource(R.string.sign_up),
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@DevicesPreview
@Composable
fun PreviewLoginScreen() {
    AquatickTheme {
        LoginScreen(navController = rememberNavController(), viewModel = hiltViewModel())
    }
}
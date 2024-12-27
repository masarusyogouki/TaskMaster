package com.example.taskmaster.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmaster.common.composable.BasicButton
import com.example.taskmaster.common.composable.EmailTextField
import com.example.taskmaster.common.composable.PasswordTextField
import com.example.taskmaster.common.composable.RepeatPasswordTextField

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavHomeClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState

    if (uiState.isSignUp) {
        SignUpScreenContent(
            uiState = uiState,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
            onSwitchState = { viewModel.switchState() },
            onSignUpClick = { viewModel.onSignUpClick(onNavHomeClick) },
            onNavHomeClick = onNavHomeClick,
        )
    } else {
        LoginScreenContent(
            uiState = uiState,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onSwitchState = { viewModel.switchState() },
            onLoginClick = { viewModel.onLoginClick(onNavHomeClick) },
            onNavHomeClick = onNavHomeClick,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreenContent(
    uiState: AuthUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSwitchState: () -> Unit,
    onSignUpClick: () -> Unit,
    onNavHomeClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors =
                    TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                title = {
                    Text(
                        "新規登録",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onNavHomeClick("home") }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "backHome",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            EmailTextField(
                value = uiState.email,
                onNewValue = onEmailChange,
            )
            PasswordTextField(
                value = uiState.password,
                onNewValue = onPasswordChange,
            )
            RepeatPasswordTextField(
                value = uiState.repeatPassword,
                onNewValue = onRepeatPasswordChange,
            )
            BasicButton(
                text = "Sign Up",
                fontColor = Color.White,
                backgroundColor = Color.Blue,
                action = onSignUpClick,
            )
            BasicButton(
                text = "アカウントをお持ちの方はこちら",
                fontColor = Color.Blue,
                backgroundColor = Color.Transparent,
                action = onSwitchState,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    uiState: AuthUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSwitchState: () -> Unit,
    onLoginClick: () -> Unit,
    onNavHomeClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors =
                    TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                title = {
                    Text(
                        "ログイン",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onNavHomeClick("home") }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "backHome",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            EmailTextField(
                value = uiState.email,
                onNewValue = onEmailChange,
            )
            PasswordTextField(
                value = uiState.password,
                onNewValue = onPasswordChange,
            )
            BasicButton(
                text = "Login",
                fontColor = Color.White,
                backgroundColor = Color.Blue,
                action = onLoginClick,
            )
            BasicButton(
                text = "アカウントを持っていない方はこちら",
                fontColor = Color.Blue,
                backgroundColor = Color.Transparent,
                action = onSwitchState,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreenContent(
        uiState =
            AuthUiState(
                email = "test@example.com",
                password = "password123",
                repeatPassword = "password123",
                isSignUp = true,
            ),
        onEmailChange = {},
        onPasswordChange = {},
        onRepeatPasswordChange = {},
        onSwitchState = {},
        onSignUpClick = {},
        onNavHomeClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreenContent(
        uiState =
            AuthUiState(
                email = "test@example.com",
                password = "password123",
                repeatPassword = "password123",
                isSignUp = false,
            ),
        onEmailChange = {},
        onPasswordChange = {},
        onSwitchState = {},
        onLoginClick = {},
        onNavHomeClick = {},
    )
}

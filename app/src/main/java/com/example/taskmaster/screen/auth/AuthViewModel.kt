package com.example.taskmaster.screen.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.common.ext.isValidEmail
import com.example.taskmaster.common.ext.isValidPassword
import com.example.taskmaster.common.ext.passwordMatches
import com.example.taskmaster.model.User
import com.example.taskmaster.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        var uiState = mutableStateOf(AuthUiState())
            private set

        val currentUser: StateFlow<User> =
            authRepository.currentUser.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = User(),
            )

        private val email
            get() = uiState.value.email
        private val password
            get() = uiState.value.password
        private val repeatPassword
            get() = uiState.value.repeatPassword
        private val isSignUp: Boolean
            get() = uiState.value.isSignUp

        fun onEmailChange(newValue: String) {
            uiState.value = uiState.value.copy(email = newValue)
        }

        fun onPasswordChange(newValue: String) {
            uiState.value = uiState.value.copy(password = newValue)
        }

        fun onRepeatPasswordChange(newValue: String) {
            uiState.value = uiState.value.copy(repeatPassword = newValue)
        }

        fun switchState() {
            uiState.value = uiState.value.copy(isSignUp = !isSignUp)
        }

        fun onLoginClick(onNavHomeClick: (String) -> Unit) {
            if (!email.isValidEmail()) {
                return
            }
            if (!password.isValidPassword()) {
                return
            }
            viewModelScope.launch {
                authRepository.signIn(email, password)
                onNavHomeClick("home")
            }
        }

        fun onSignUpClick(onNavHomeClick: (String) -> Unit) {
            if (!email.isValidEmail()) {
                return
            }
            if (!password.isValidPassword()) {
                return
            }
            if (!password.passwordMatches(repeatPassword)) {
                return
            }
            viewModelScope.launch {
                authRepository.register(email, password)
                onNavHomeClick("home")
            }
        }
    }

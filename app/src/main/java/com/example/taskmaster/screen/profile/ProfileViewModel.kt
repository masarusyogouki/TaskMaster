package com.example.taskmaster.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun onLogOutClick(onNavHomeClick: (String) -> Unit) {
        viewModelScope.launch {
            authRepository.logOut()
            onNavHomeClick("home")
        }
    }
}
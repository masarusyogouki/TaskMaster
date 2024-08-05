package com.example.taskmaster

import androidx.navigation.NavHostController
import com.example.taskmaster.Destinations.EDIT_SCREEN

class TodoNavigationActions(
    private val navController: NavHostController,
) {
    fun navigateToEditScreen() {
        navController.navigate(EDIT_SCREEN)
    }
}

package com.example.taskmaster

import androidx.navigation.NavHostController
import com.example.taskmaster.model.Task

class TodoNavigationActions(
    private val navController: NavHostController,
) {
    fun navigateToEditScreen(task: Task) {
        navController.navigate("${Destinations.EDIT_SCREEN}/${task.id}")
    }
}

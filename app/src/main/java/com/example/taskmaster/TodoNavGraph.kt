package com.example.taskmaster

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskmaster.screen.edit.EditScreen
import com.example.taskmaster.screen.home.HomeScreen

@Composable
fun TodoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME_SCREEN,
    navActions: TodoNavigationActions =
        remember(navController) {
            TodoNavigationActions(
                navController,
            )
        },
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Destinations.HOME_SCREEN) {
                HomeScreen(
                    onNavEditClick = navActions::navigateToEditScreen,
                )
            }

            composable(
                route = "${Destinations.EDIT_SCREEN}/{taskId}",
                arguments = listOf(navArgument("taskId") { type = NavType.LongType }),
            ) { backStackEntry ->
                val taskId = backStackEntry.arguments?.getLong("taskId")
                requireNotNull(taskId) { "Task ID is required" }
                EditScreen(
                    taskId = taskId,
                    navController = navController,
                )
            }
        }
    }
}

object Destinations {
    const val HOME_SCREEN = "home"
    const val EDIT_SCREEN = "edit"
}

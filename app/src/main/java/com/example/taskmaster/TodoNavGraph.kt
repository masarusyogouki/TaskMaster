package com.example.taskmaster

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmaster.screen.edit.EditScreen
import com.example.taskmaster.screen.home.HomeScreen

@Composable
fun TodoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME_SCREEN,
) {
    val navActions = remember(navController) { TodoNavigationActions(navController) }

    Scaffold(
        modifier = modifier,
        bottomBar = { Text(text = "bottombar") },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Destinations.HOME_SCREEN) {
                HomeScreen(navController)
            }

            composable(
                route = Destinations.EDIT_SCREEN,
            ) {
                EditScreen()
            }
        }
    }
}

object Destinations {
    const val HOME_SCREEN = "home"
    const val EDIT_SCREEN = "edit"
}

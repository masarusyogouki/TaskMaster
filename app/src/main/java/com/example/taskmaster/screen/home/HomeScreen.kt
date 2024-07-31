package com.example.taskmaster.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    HomeScreenContent(
        onAddClick = viewModel::addTask,
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onAddClick: (String) -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
    ) {
        Text(text = "Home Screen")
    }
}

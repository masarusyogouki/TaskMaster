package com.example.taskmaster.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmaster.common.composable.NewTaskField

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.value

    HomeScreenContent(
        uiState = uiState,
        onAddClick = viewModel::addTask,
        onTitleChange = viewModel::onTitleChange,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onTitleChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onAddClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home Screen") },
            )
        },
    ) { innerPadding ->
        Box(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
            ) {
                // 画面上部のコンテンツ
            }

            NewTaskField(
                value = uiState.title,
                onNewValue = onTitleChange,
                onClick = { onAddClick(uiState.title) },
                modifier =
                    Modifier
                        .align(Alignment.BottomCenter),
            )
        }
    }
}

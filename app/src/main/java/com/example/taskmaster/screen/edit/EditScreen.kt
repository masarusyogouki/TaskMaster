package com.example.taskmaster.screen.edit

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EditScreen(viewModel: EditViewModel = hiltViewModel()) {
    EditScreenContent()
}

@SuppressLint("ComposeModifierMissing")
@Composable
fun EditScreenContent() {
    Text("Edit Screen")
}

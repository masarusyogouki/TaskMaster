package com.example.taskmaster.common.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BasicButton(
    text: String,
    fontColor: Color,
    backgroundColor: Color,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = action,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 10.dp,
                horizontal = 24.dp
            ),
        colors = ButtonDefaults.buttonColors(
            contentColor = fontColor,
            containerColor = backgroundColor
        )
    ) {
        Text(text = text)
    }
}
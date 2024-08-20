package com.example.taskmaster.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.ui.theme.DarkGray
import com.example.taskmaster.ui.theme.MilkyWhite
import com.example.taskmaster.ui.theme.PaleBlue

@Composable
fun NewTaskField(
    value: String,
    onNewValue: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        singleLine = true,
        modifier =
            modifier
                .padding(8.dp)
                .height(60.dp)
                .fillMaxWidth(),
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = {
            Text(
                text = "新しいタスクを追加",
                color = DarkGray,
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "追加",
                    modifier = Modifier.clickable { onClick() },
                    tint = PaleBlue,
                )
            }
        },
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = MilkyWhite,
                unfocusedContainerColor = MilkyWhite,
                disabledContainerColor = MilkyWhite,
                focusedIndicatorColor = MilkyWhite,
                unfocusedIndicatorColor = MilkyWhite,
            ),
        shape = RoundedCornerShape(20.dp),
    )
}

@Composable
fun BasicTextField(
    value: String,
    newValue: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = { newValue(it) },
        textStyle = TextStyle(fontSize = 16.sp),
        modifier = modifier,
        singleLine = true,
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = MilkyWhite,
                unfocusedContainerColor = MilkyWhite,
                disabledContainerColor = MilkyWhite,
                focusedIndicatorColor = MilkyWhite,
                unfocusedIndicatorColor = MilkyWhite,
            ),
    )
}

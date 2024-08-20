package com.example.taskmaster.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.model.Priority
import com.example.taskmaster.ui.theme.Blue
import com.example.taskmaster.ui.theme.Gray
import com.example.taskmaster.ui.theme.MilkyWhite
import com.example.taskmaster.ui.theme.PaleBlue
import com.example.taskmaster.ui.theme.Red
import com.example.taskmaster.ui.theme.Yellow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    dueDate: LocalDate?,
    onDueDateChange: (LocalDate?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dateText = dueDate?.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) ?: "日付を選択"
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { showDatePicker = true },
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "日付選択",
                tint = if (dueDate != null) PaleBlue else Color.Black,
            )
        }
        TextField(
            value = dateText,
            onValueChange = {},
            textStyle =
                TextStyle(
                    fontSize = 16.sp,
                ),
            readOnly = true,
            colors =
                TextFieldDefaults.colors(
                    focusedContainerColor = MilkyWhite,
                    unfocusedContainerColor = MilkyWhite,
                    disabledContainerColor = MilkyWhite,
                    focusedIndicatorColor = MilkyWhite,
                    unfocusedIndicatorColor = MilkyWhite,
                ),
        )
        if (dueDate != null) {
            IconButton(onClick = { onDueDateChange(null) }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "削除",
                    tint = Color.Black,
                )
            }
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDate =
                            datePickerState.selectedDateMillis?.let {
                                Instant
                                    .ofEpochMilli(it)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                            }
                        onDueDateChange(selectedDate)
                        showDatePicker = false
                    },
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("キャンセル")
                }
            },
            modifier = Modifier.padding(16.dp),
            content = {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
        )
    }
}

@Composable
fun PriorityDialog(
    currentPriority: Priority,
    onPriorityChange: (Priority) -> Unit,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }
    var selectedPriority by remember { mutableStateOf(currentPriority) }

    if (visible) {
        AlertDialog(
            onDismissRequest = { visible = false },
            title = {
                Text(
                    text = "優先度を選択",
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            text = {
                Column {
                    Priority.entries.forEach { priority ->
                        Text(
                            text = priority.name,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .background(
                                        when (priority) {
                                            Priority.LOW -> Blue
                                            Priority.MEDIUM -> Yellow
                                            Priority.HIGH -> Red
                                            Priority.NONE -> Gray
                                        },
                                    ).clickable {
                                        selectedPriority = priority
                                        onPriorityChange(priority)
                                        visible = false
                                    },
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { visible = false },
                ) {
                    Text(text = "Cancel")
                }
            },
        )
    }

    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { visible = true }) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Priority",
                tint = if (currentPriority != Priority.NONE) PaleBlue else Color.Black,
            )
        }
        TextField(
            value = currentPriority.name,
            onValueChange = {},
            textStyle =
                TextStyle(
                    fontSize = 16.sp,
                ),
            readOnly = true,
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
}

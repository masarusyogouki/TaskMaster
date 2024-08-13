package com.example.taskmaster.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmaster.model.Priority
import com.example.taskmaster.ui.theme.DarkGray
import com.example.taskmaster.ui.theme.MilkyWhite
import com.example.taskmaster.ui.theme.PaleBlue
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

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
                .height(48.dp)
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
        modifier = modifier,
        singleLine = true,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    onDueDateChange: (LocalDate?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberDatePickerState()
    var visible by remember { mutableStateOf(false) }

    val selectedDate by remember {
        derivedStateOf {
            state.selectedDateMillis?.let { millis ->
                val date = Date(millis)
                SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(date)
            } ?: "No date selected"
        }
    }

    if (visible) {
        DatePickerDialog(
            onDismissRequest = { visible = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedMillis = state.selectedDateMillis
                        val localDate =
                            selectedMillis?.let { millis ->
                                Instant
                                    .ofEpochMilli(millis)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                            }
                        onDueDateChange(localDate)
                        visible = false
                    },
                ) {
                    Text(text = "OK")
                }
            },
        ) {
            DatePicker(state = state)
        }
    }

    Text(
        text =
            if (selectedDate == "No date selected") {
                "期限日を追加"
            } else {
                selectedDate
            },
        modifier = modifier.clickable { visible = true },
    )
}

@Composable
fun PriorityField(
    currentPriority: Priority,
    onPriorityChange: (Priority) -> Unit,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }
    var selectedPriority by remember { mutableStateOf(currentPriority) }

    if (visible) {
        AlertDialog(
            onDismissRequest = { visible = false },
            title = { Text(text = "Select Priority") },
            text = {
                Column {
                    Priority.entries.forEach { priority ->
                        Text(
                            text = priority.name,
                            modifier =
                                Modifier
                                    .clickable {
                                        selectedPriority = priority
                                        onPriorityChange(priority)
                                        visible = false
                                    }.padding(8.dp),
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

    Text(
        text = selectedPriority.name,
        modifier = modifier.clickable { visible = true },
    )
}

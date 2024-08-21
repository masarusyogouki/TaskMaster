package com.example.taskmaster.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.R
import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import com.example.taskmaster.ui.theme.Blue
import com.example.taskmaster.ui.theme.Gray
import com.example.taskmaster.ui.theme.MilkyWhite
import com.example.taskmaster.ui.theme.PaleBlue
import com.example.taskmaster.ui.theme.Red
import com.example.taskmaster.ui.theme.Yellow
import java.time.LocalDate

@Composable
fun TaskCard(
    task: Task,
    onCompletedChange: (Boolean) -> Unit,
    onDeleteTask: (Task) -> Unit,
    onNavEditClick: (Task) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(4.dp)
                .clickable { onNavEditClick(task) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter =
                    if (task.isCompleted) {
                        painterResource(id = R.drawable.expand_circle_down)
                    } else {
                        painterResource(id = R.drawable.radio_button_unchecked)
                    },
                contentDescription = "isCompleted",
                modifier =
                    Modifier
                        .size(30.dp)
                        .clickable { onCompletedChange(!task.isCompleted) },
                tint = if (task.isCompleted) PaleBlue else Color.Black,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = task.title,
                modifier =
                    Modifier
                        .weight(1f),
                fontSize = 16.sp,
                maxLines = 1,
                style =
                    TextStyle(
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
                    ),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier =
                    Modifier
                        .height(30.dp)
                        .width(60.dp)
                        .background(
                            when (task.priority) {
                                Priority.LOW -> Blue
                                Priority.MEDIUM -> Yellow
                                Priority.HIGH -> Red
                                Priority.NONE -> Gray
                            },
                            shape = RoundedCornerShape(20.dp),
                        ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text =
                        when (task.priority) {
                            Priority.LOW -> "Low"
                            Priority.MEDIUM -> "Medium"
                            Priority.HIGH -> "High"
                            Priority.NONE -> "None"
                        },
                    style =
                        TextStyle(
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        ),
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Delete",
                modifier =
                    Modifier
                        .size(30.dp)
                        .clickable { onDeleteTask(task) },
                tint = PaleBlue,
            )
        }
    }
}

@Composable
fun EditTitleCard(
    title: String,
    newValue: (String) -> Unit,
    isCompleted: Boolean,
    onCompletedChange: (Boolean) -> Unit,
    updateTitle: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(2.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MilkyWhite,
                contentColor = Color.Black,
            ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter =
                    if (isCompleted) {
                        painterResource(id = R.drawable.expand_circle_down)
                    } else {
                        painterResource(id = R.drawable.radio_button_unchecked)
                    },
                contentDescription = "isCompleted",
                modifier =
                    Modifier
                        .size(32.dp)
                        .clickable { onCompletedChange(!isCompleted) },
                tint = if (isCompleted) PaleBlue else Color.Black,
            )
            BasicTextField(
                value = title,
                newValue = { newValue(it) },
                onDone = { updateTitle(title) },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
fun EditDialogCard(
    task: Task?,
    onDueDateChange: (LocalDate?) -> Unit,
    onPriorityChange: (Priority) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth(),
        colors =
            CardDefaults.cardColors(
                containerColor = MilkyWhite,
                contentColor = Color.Black,
            ),
    ) {
        Column {
            task?.let { taskDetail ->
                DatePickerDialog(
                    dueDate = taskDetail.dueDate,
                    onDueDateChange = onDueDateChange,
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 2.dp,
                    color = Color.Gray,
                )
                PriorityDialog(
                    currentPriority = taskDetail.priority,
                    onPriorityChange = onPriorityChange,
                )
            }
        }
    }
}

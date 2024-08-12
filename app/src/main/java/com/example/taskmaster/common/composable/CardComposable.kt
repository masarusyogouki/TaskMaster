package com.example.taskmaster.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskmaster.R
import com.example.taskmaster.model.Task

@Composable
fun TaskCard(
    task: Task,
    onCompletedChange: (Boolean) -> Unit,
    onNavEditClick: (Task) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(2.dp)
                .clickable { onNavEditClick(task) },
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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
                        .size(24.dp)
                        .clickable { onCompletedChange(!task.isCompleted) },
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = task.title,
                modifier =
                    Modifier
                        .weight(1f)
                        .size(24.dp),
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
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(2.dp),
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
            )
            BasicTextField(
                value = title,
                newValue = { newValue(it) },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

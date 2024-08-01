package com.example.taskmaster.common.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskmaster.R
import com.example.taskmaster.model.Priority

@Composable
fun TaskCard(
    isCompleted: Boolean,
    title: String,
    priority: Priority,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
        ) {
            Icon(
                painter =
                    if (isCompleted) {
                        painterResource(id = R.drawable.expand_circle_down)
                    } else {
                        painterResource(id = R.drawable.radio_button_unchecked)
                    },
                contentDescription = "isCompleted",
                modifier = Modifier.size(24.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                modifier = Modifier.weight(1f),
            )

            Icon(
                imageVector =
                    if (priority == Priority.None) {
                        Icons.Sharp.Star
                    } else {
                        Icons.Filled.Star
                    },
                contentDescription = "priority",
                tint =
                    if (priority == Priority.None) {
                        Color.Gray.copy(alpha = 0.3f)
                    } else {
                        Color.Yellow
                    },
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

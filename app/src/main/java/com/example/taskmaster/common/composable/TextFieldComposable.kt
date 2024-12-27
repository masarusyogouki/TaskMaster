package com.example.taskmaster.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.R
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
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = { newValue(it) },
        textStyle = TextStyle(fontSize = 16.sp),
        modifier = modifier,
        singleLine = true,
        keyboardOptions =
            KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
            ),
        keyboardActions =
            KeyboardActions(
                onDone = { onDone() },
            ),
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

@Composable
fun EmailTextField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text("メールアドレスを入力してください") },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    PasswordField(value, onNewValue, modifier)
}

@Composable
fun RepeatPasswordTextField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    PasswordField(value, onNewValue, modifier)
}

@Composable
fun PasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isVisible by remember { mutableStateOf(false) }

    val icon =
        if (isVisible) {
            painterResource(R.drawable.ic_visibility_on)
        } else {
            painterResource(R.drawable.ic_visibility_off)
        }

    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text("パスワードを入力してください") },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(painter = icon, contentDescription = "Visibility")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation,
    )
}

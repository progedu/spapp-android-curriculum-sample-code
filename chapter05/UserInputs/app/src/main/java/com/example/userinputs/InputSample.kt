package com.example.userinputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSample() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("サンプル") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            SliderSample()
        }
    }
}

@Composable
fun SliderSample() {
    var volume by remember { mutableFloatStateOf(50f) }

    Text(text = "音量の調整")
    Spacer(Modifier.height(4.dp))
    Slider(
        value = volume,
        onValueChange = { volume = it },
        valueRange = 0f..100f,
        steps = 9
    )
    Spacer(Modifier.height(8.dp))
    Text("${(volume).toInt()}")
}

@Composable
fun SwitchSample() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("通知を受け取る")
        Spacer(Modifier.width(16.dp))
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
    Spacer(Modifier.height(8.dp))
    Text("通知: ${if (isChecked) "オン" else "オフ" }")
}

@Composable
fun CheckboxSample() {
    val fruits = listOf("りんご", "バナナ", "みかん", "ぶどう", "もも")
    var selectedFruits by remember { mutableStateOf( setOf<String>()) }

    fruits.forEach { fruit ->
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = selectedFruits.contains(fruit),
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        selectedFruits += fruit
                    } else {
                        selectedFruits -= fruit
                    }
                }
            )
            Text(fruit)
        }
    }
    Spacer(Modifier.height(8.dp))
    Text("選択中: ${selectedFruits.joinToString()}")
}

@Composable
fun RadioButtonSample() {
    var selectedText by remember { mutableStateOf("はい") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable{ selectedText = "はい" }
    ) {
        RadioButton(
            selected = selectedText == "はい",
            onClick = { selectedText = "はい" }
        )
        Text(text = "はい")
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable{ selectedText = "いいえ" }
    ) {
        RadioButton(
            selected = selectedText == "いいえ",
            onClick = { selectedText = "いいえ" }
        )
        Text("いいえ")
    }
    Spacer(Modifier.height(8.dp))
    Text("選択しているボタン：${selectedText}")
}

@Composable
private fun TextFieldSample() {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    MyTextField(
        value = name,
        onValueChanged = { name = it },
        label = "名前"
    )
    Spacer(Modifier.height(12.dp))
    MyTextField(
        value = password,
        onValueChanged = { password = it },
        label = "パスワード",
        trailingIcon = Icons.Default.Lock,
        visualTransformation = PasswordVisualTransformation()
    )
    Spacer(Modifier.height(12.dp))
    MyTextField(
        value = "",
        onValueChanged = {},
        label = "メールアドレス"
    )
    Spacer(Modifier.height(12.dp))
    MyTextField(
        value = "",
        onValueChanged = {},
        label = "電話番号",
        trailingIcon = Icons.Default.Phone
    )
}

@Composable
fun MyTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    trailingIcon: ImageVector? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(label) },
        trailingIcon = {
            trailingIcon?.let {
                Icon(imageVector = it, contentDescription = null)
            }
        },
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
private fun InputSamplePreview() {
    InputSample()
}
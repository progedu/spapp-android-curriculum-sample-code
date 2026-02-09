package com.example.todoapp

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.model.Priority
import com.example.todoapp.model.Task
import com.example.todoapp.model.TodoViewModel

@Composable
fun TodoAppScreen(
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel = viewModel()
) {
    val name = viewModel.name
    val priority = viewModel.priority
    val tasks = viewModel.tasks
    val taskToDelete = viewModel.taskToDelete

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        // ヨコ向き
        Row(
            modifier = modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TaskInputSection(
                    name = name,
                    onNameChange = viewModel::updateName,
                    priority = priority,
                    onPriorityChange = viewModel::updatePriority,
                    onAddTask = viewModel::addTask
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TaskListSection(
                    tasks = tasks,
                    onDeleteTask = viewModel::showDeleteDialog
                )
            }
        }
    } else {
        // タテ向き
        Column(
            modifier = modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TaskInputSection(
                name = name,
                onNameChange = viewModel::updateName,
                priority = priority,
                onPriorityChange = viewModel::updatePriority,
                onAddTask = viewModel::addTask
            )

            HorizontalDivider()

            TaskListSection(
                tasks = tasks,
                onDeleteTask = viewModel::showDeleteDialog
            )
        }
    }

    if (taskToDelete != null) {
        AlertDialog(
            onDismissRequest = viewModel::dismissDeleteDialog,
            confirmButton = {
                TextButton(
                    onClick = viewModel::confirmDeleteTask
                ) {
                    Text("削除")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = viewModel::dismissDeleteDialog
                ) {
                    Text("キャンセル")
                }
            },
            title = { Text("確認") },
            text = { Text("このタスクを削除しますか？") }
        )
    }
}

@Composable
private fun TaskInputSection(
    name: String,
    onNameChange: (String) -> Unit,
    priority: String,
    onPriorityChange: (String) -> Unit,
    onAddTask: () -> Unit
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("タスク名") },
        modifier = Modifier.fillMaxWidth()
    )

    Text("優先度を選択")

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Priority.entries.map { it.label }.forEach {
            RadioButton(
                selected = priority == it,
                onClick = { onPriorityChange(it) }
            )
            Text(it)
            Spacer(Modifier.width(8.dp))
        }
    }

    Button(
        onClick = onAddTask,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("タスクを追加")
    }
}

@Composable
private fun TaskListSection(
    tasks: List<Task>,
    onDeleteTask: (Task) -> Unit
) {
    Text(text = "登録済みのタスク", style = MaterialTheme.typography.titleMedium)

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks) { task ->
            TaskCard(
                task = task,
                onDelete = { onDeleteTask(task) }
            )
        }
    }
}

@Composable
private fun TaskCard(task: Task, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = getContainerColor(task.priority)
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(task.name, style = MaterialTheme.typography.bodyLarge)
                Text("優先度: ${task.priority}", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(
                onClick = onDelete
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "削除",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun getContainerColor(priority: String): Color {
    return when (priority) {
        Priority.HIGH.label -> MaterialTheme.colorScheme.errorContainer
        Priority.MEDIUM.label -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.surfaceContainer
    }
}

@Preview(name = "タテ向きレイアウト", showSystemUi = true, device = Devices.PIXEL_4)
@Preview(name = "ヨコ向きレイアウト", showSystemUi = true, device = "spec:width=915dp,height=412dp,dpi=420")
@Composable
private fun TodoAppScreenPreview() {
    MainScreen()
}
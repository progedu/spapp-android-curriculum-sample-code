package com.example.todoapp

import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.todoapp.model.Priority
import com.example.todoapp.model.Task
import com.example.todoapp.model.TodoViewModel
import com.example.todoapp.ui.theme.TodoAppTheme

@Composable
fun TodoAppScreen(
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel
) {
    TodoAppContent(
        modifier = modifier,
        name = viewModel.name,
        priority = viewModel.priority,
        tasks = viewModel.tasks,
        taskToDelete = viewModel.taskToDelete,
        onNameChange = viewModel::updateName,
        onPriorityChange = viewModel::updatePriority,
        onSaveTask = viewModel::saveTask,
        onDeleteIconClick = viewModel::showDeleteDialog,
        onConfirmDelete = viewModel::confirmDeleteTask,
        onDismissDialog = viewModel::dismissDeleteDialog,
        isEditing = viewModel.taskToEdit != null,
        onTaskClick = viewModel::startEditing,
        onCancelEdit = viewModel::cancelEditing
    )
}

@Composable
fun TodoAppContent(
    modifier: Modifier = Modifier,
    name: String,
    priority: String,
    tasks: List<Task>,
    taskToDelete: Task?,
    onNameChange: (String) -> Unit,
    onPriorityChange: (String) -> Unit,
    onSaveTask: () -> Unit,
    onDeleteIconClick: (Task) -> Unit,
    onConfirmDelete: () -> Unit,
    onDismissDialog: () -> Unit,
    isEditing: Boolean,
    onTaskClick: (Task) -> Unit,
    onCancelEdit: () -> Unit
) {
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
                    onNameChange = onNameChange,
                    priority = priority,
                    onPriorityChange = onPriorityChange,
                    onAddTask = onSaveTask,
                    isEditing = isEditing,
                    onCancelEdit = onCancelEdit
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TaskListSection(
                    tasks = tasks,
                    onDeleteTask = onDeleteIconClick,
                    onTaskClick = onTaskClick
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
                onNameChange = onNameChange,
                priority = priority,
                onPriorityChange = onPriorityChange,
                onAddTask = onSaveTask,
                isEditing = isEditing,
                onCancelEdit = onCancelEdit
            )

            HorizontalDivider()

            TaskListSection(
                tasks = tasks,
                onDeleteTask = onDeleteIconClick,
                onTaskClick = onTaskClick
            )
        }
    }

    if (taskToDelete != null) {
        AlertDialog(
            onDismissRequest = onDismissDialog,
            confirmButton = {
                TextButton(
                    onClick = onConfirmDelete
                ) {
                    Text("削除")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissDialog
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
    onAddTask: () -> Unit,
    isEditing: Boolean,
    onCancelEdit: () -> Unit
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
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEditing) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
        )
    ) {
        Text(if (isEditing) "タスクを更新" else "タスクを追加")
    }

    if (isEditing) {
        OutlinedButton(
            onClick = onCancelEdit,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("キャンセル", color = Color.DarkGray)
        }
    }
}

@Composable
private fun TaskListSection(
    tasks: List<Task>,
    onDeleteTask: (Task) -> Unit,
    onTaskClick: (Task) -> Unit
) {
    Text(text = "登録済みのタスク", style = MaterialTheme.typography.titleMedium)

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks) { task ->
            TaskCard(
                task = task,
                onDelete = { onDeleteTask(task) },
                onClick = { onTaskClick(task) }
            )
        }
    }
}

@Composable
private fun TaskCard(
    task: Task,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable{ onClick() },
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
    TodoAppTheme {
        TodoAppContent(
            modifier = Modifier.padding(top = 24.dp),
            name = "",
            priority = Priority.MEDIUM.label,
            tasks = listOf(
                Task(0, "宿題を終わらせる", Priority.HIGH.label),
                Task(0, "友達にノートを借りる", Priority.MEDIUM.label),
                Task(0, "好きな音楽を聴く", Priority.LOW.label)
            ),
            taskToDelete = null,
            onNameChange = {},
            onPriorityChange = {},
            onSaveTask = {},
            onDeleteIconClick = {},
            onConfirmDelete = {},
            onDismissDialog = {},
            isEditing = false,
            onTaskClick = {},
            onCancelEdit = {}
        )
    }
}

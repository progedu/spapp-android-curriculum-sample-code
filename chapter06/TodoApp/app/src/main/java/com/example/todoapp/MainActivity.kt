package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todoapp.model.TodoViewModel
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: TodoViewModel = viewModel(
                factory = viewModelFactory {
                    initializer {
                        TodoViewModel(applicationContext)
                    }
                }
            )
            MainScreen(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: TodoViewModel) {
    TodoAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = if (viewModel.taskToEdit == null) "タスクを追加" else "編集中") },
                    navigationIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) }
                )
            }
        ) { paddingValues ->
            TodoAppScreen(modifier = Modifier.padding(paddingValues), viewModel = viewModel)
        }
    }
}
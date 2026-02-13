package com.example.todoapp.model

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.AppDatabase
import kotlinx.coroutines.launch

class TodoViewModel(context: Context) : ViewModel() {

    private val db = AppDatabase.getDatabase(context)
    private val taskDao = db.taskDao()

    var name by mutableStateOf("")
        private set
    var priority by mutableStateOf(Priority.MEDIUM.label)
        private set
    var tasks by mutableStateOf<List<Task>>(emptyList())
        private set
    var taskToDelete by mutableStateOf<Task?>(null)
        private set
    var taskToEdit by mutableStateOf<Task?>(null)
        private set

    init {
        // Flow を監視し、データが変わるたびに tasks を更新
        viewModelScope.launch {
            taskDao.getAllTasks().collect {
                tasks = it
            }
        }
    }

    // タスク名を入力したときの処理
    fun updateName(newName: String) {
        name = newName
    }

    // 優先度を選択したときの処理
    fun updatePriority(newPriority: String) {
        priority = newPriority
    }

    // 追加ボタンを押したときの処理（タスクの追加）
    fun saveTask() {
        if (name.isBlank()) return

        viewModelScope.launch {
            val task = if (taskToEdit == null) {
                Task(name = name, priority = priority)
            } else {
                taskToEdit!!.copy(name = name, priority = priority)
            }

            if (taskToEdit == null) {
                // 新規追加
                taskDao.insert(task)
            } else {
                // 更新
                taskDao.update(task)
            }

            cancelEditing()
        }
    }

    // 編集開始
    fun startEditing(task: Task) {
        taskToEdit = task
        name = task.name
        priority = task.priority
    }

    // 編集キャンセル
    fun cancelEditing() {
        taskToEdit = null
        name = ""
        priority = Priority.MEDIUM.label
    }

    // 削除アイコンがタップされて、ダイアログを表示する処理
    fun showDeleteDialog(task: Task) {
        taskToDelete = task
    }

    // タスクを削除して、ダイアログを閉じる処理
    fun confirmDeleteTask() {
        taskToDelete?.let { task ->
            viewModelScope.launch {
                taskDao.delete(task)
            }
        }
        dismissDeleteDialog()
    }

    // ダイアログを閉じる処理
    fun dismissDeleteDialog() {
        taskToDelete = null
    }
}
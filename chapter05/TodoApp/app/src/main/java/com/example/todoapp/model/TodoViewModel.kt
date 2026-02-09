package com.example.todoapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    var name by mutableStateOf("")
    private set
    var priority by mutableStateOf(Priority.MEDIUM.label)
    private set
    var tasks = mutableStateListOf<Task>()
    private set
    var taskToDelete by mutableStateOf<Task?>(null)
    private set

    // タスク名を入力したときの処理
    fun updateName(newName: String) {
        name = newName
    }

    // 優先度を選択したときの処理
    fun updatePriority(newPriority: String) {
        priority = newPriority
    }

    // 追加ボタンを押したときの処理（タスクの追加）
    fun addTask() {
        if (name.isNotBlank()) {
            tasks.add(Task(name, priority))
            name = ""
            priority =  Priority.MEDIUM.label
        }
    }

    // 削除アイコンがタップされて、ダイアログを表示する処理
    fun showDeleteDialog(task: Task) {
        taskToDelete = task
    }

    // タスクを削除して、ダイアログを閉じる処理
    fun confirmDeleteTask() {
        taskToDelete?.let {
            tasks.remove(it)
        }
        dismissDeleteDialog()
    }

    // ダイアログを閉じる処理
    fun dismissDeleteDialog() {
        taskToDelete = null
    }
}
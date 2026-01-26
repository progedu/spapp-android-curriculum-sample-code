package com.example.composableproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : Screen("home", "ホーム", Icons.Default.Home)
    data object ColumnAndRow : Screen("column_and_row", "Column & Row", Icons.Default.Face)
    data object UserList : Screen("user_list", "ユーザリスト", Icons.Default.Menu)
}
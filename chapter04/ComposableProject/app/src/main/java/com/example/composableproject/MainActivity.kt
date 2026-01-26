package com.example.composableproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composableproject.ui.theme.ComposableProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableProjectTheme(
                dynamicColor = false
            ) {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screens = listOf(Screen.Home, Screen.ColumnAndRow, Screen.UserList)
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val currentTitle = when (currentRoute) {
        Screen.Home.route -> Screen.Home.title
        Screen.ColumnAndRow.route -> Screen.ColumnAndRow.title
        Screen.UserList.route -> Screen.UserList.title
        else -> Screen.Home.title
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(currentTitle)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    screens.forEach { screen ->
                        IconButton(
                            onClick = { navController.navigate(screen.route) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(screen.icon, contentDescription = null)
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.ColumnAndRow.route) { ColumnAndRowSample() }
            composable(Screen.UserList.route) { ListSample() }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.navigate(Screen.ColumnAndRow.route)
            }
        ) {
            Text(Screen.ColumnAndRow.title)
        }
        Button(
            onClick = {
                navController.navigate(Screen.UserList.route)
            }
        ) {
            Text(Screen.UserList.title)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    ComposableProjectTheme(
        dynamicColor = false
    ) {
        MainScreen()
    }
}
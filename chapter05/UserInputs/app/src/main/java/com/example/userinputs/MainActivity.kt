package com.example.userinputs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.userinputs.ui.theme.UserInputsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserInputsTheme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen() {
    InputSample()
}

@Preview(showBackground = true)
@Composable
private fun AppScreenPreview() {
    UserInputsTheme {
        AppScreen()
    }
}
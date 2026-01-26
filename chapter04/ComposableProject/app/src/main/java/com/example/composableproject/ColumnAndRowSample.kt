package com.example.composableproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composableproject.ui.theme.DeepPink
import com.example.composableproject.ui.theme.Emerald
import com.example.composableproject.ui.theme.SunsetOrange
import com.example.composableproject.ui.theme.Teal

@Composable
fun ColumnAndRowSample() {
    PracticeLayout()
}

@Composable
private fun PracticeLayout() {
    AnswerOne()
    //AnswerTwo()
}

@Composable
private fun AnswerOne() {
    Column {
        Row(
            Modifier.weight(1f)
        ) {
            MyBox("1", Emerald, Modifier.weight(1f))
            MyBox("2", DeepPink, Modifier.weight(1f))
        }
        Row(
            Modifier.weight(1f)
        ) {
            MyBox("3", SunsetOrange, Modifier.weight(1f))
            MyBox("4", Teal, Modifier.weight(1f))
        }
    }
}

@Composable
private fun AnswerTwo() {
    Row {
        Column(
            Modifier.weight(1f)
        ) {
            MyBox("1", Emerald, Modifier.weight(1f))
            MyBox("3", SunsetOrange, Modifier.weight(1f))
        }
        Column(
            Modifier.weight(1f)
        ) {
            MyBox("2", DeepPink, Modifier.weight(1f))
            MyBox("4", Teal, Modifier.weight(1f))
        }
    }
}

@Composable
private fun RowLayout() {
    Row (
        modifier = Modifier.fillMaxSize()
    ) {
        MyBox(
            text = "1",
            color = Emerald,
            modifier = Modifier.weight(1f)
        )
        MyBox(
            text = "2",
            color = DeepPink,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ColumnLayout() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MyBox(
            text = "1",
            color = Emerald,
            modifier = Modifier.weight(1f)
        )
        MyBox(
            text = "2",
            color = DeepPink,
            modifier = Modifier.weight(2f)
        )
        MyBox(
            text = "3",
            color = SunsetOrange,
            modifier = Modifier.weight(3f)
        )
        MyBox(
            text = "4",
            color = Teal,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun MyBox(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
            Image(
                imageVector = Icons.Default.Face,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ColumnAndRowSamplePreview() {
    //ColumnAndRowSample()
}

@Preview(showBackground = true)
@Composable
private fun ColumnLayoutPreview() {
    ColumnLayout()
}

@Preview(showBackground = true)
@Composable
private fun RowLayoutPreview() {
    RowLayout()
}

@Preview(showBackground = true)
@Composable
private fun MyBoxPreview() {
    MyBox(text = "Preview", color = Emerald)
}

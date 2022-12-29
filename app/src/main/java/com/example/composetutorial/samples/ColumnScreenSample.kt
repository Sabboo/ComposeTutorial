package com.example.composetutorial

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ColumnScreenSample() {
    Surface(
        color = Color.DarkGray,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            GreetingText(name = "Ahmed")
            GreetingText(name = "Ali")
            GreetingText(name = "Mohamed")
            GreetingText(name = "Mostafa")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnScreenSamplePreview() {
    ColumnScreenSample()
}

package com.example.composetutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DynamicListScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val namesListState = remember { mutableStateListOf<String>() }
        val newNameTextFieldState = remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (name in namesListState) {
                ListItem(text = name)
            }

            TextField(
                value = newNameTextFieldState.value,
                onValueChange = { newText -> newNameTextFieldState.value = newText })

            Button(onClick = { namesListState.add(newNameTextFieldState.value) }) {
                Text("Add new name")
            }
        }
    }
}

@Composable
fun ListItem(text: String) {
    Text(text = text, style = MaterialTheme.typography.bodyMedium)
}

@Preview(showBackground = true)
@Composable
fun DynamicListScreenPreview() {
    DynamicListScreen()
}
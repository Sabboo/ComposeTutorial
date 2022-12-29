package com.example.composetutorial.dynamic_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun DynamicListScreen(viewModel: DynamicListViewModel = viewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val namesListState = remember { viewModel.namesListState }
        val newNameTextFieldState = viewModel.nameTextFieldFlow.collectAsState("")

        DynamicListScreenContent(
            namesList = namesListState,
            newNameText = newNameTextFieldState.value,
            onTextFieldValueChange = { newName -> viewModel.onTextFieldValueChange(newName) },
            onAddNewNameButtonClick = { newName -> viewModel.onAddNewNameButtonClick(newName) }
        )
    }
}

@Composable
fun DynamicListScreenContent(
    namesList: List<String>,
    newNameText: String,
    onTextFieldValueChange: (newName: String) -> Unit,
    onAddNewNameButtonClick: (newName: String) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (name in namesList) {
                ListItem(text = name)
            }

            TextField(
                value = newNameText,
                onValueChange = { newName -> onTextFieldValueChange(newName) })

            Button(onClick = { onAddNewNameButtonClick(newNameText) }) {
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
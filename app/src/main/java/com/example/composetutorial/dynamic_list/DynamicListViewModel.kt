package com.example.composetutorial.dynamic_list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DynamicListViewModel : ViewModel() {

    var namesListState = mutableStateListOf<String>()
    val nameTextFieldFlow = MutableStateFlow("")

    fun onAddNewNameButtonClick(value: String) {
        namesListState.add(value)
    }

    fun onTextFieldValueChange(newText: String) {
        nameTextFieldFlow.value = newText
    }
}
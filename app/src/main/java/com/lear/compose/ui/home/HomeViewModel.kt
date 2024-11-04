package com.lear.compose.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    fun increase() {
        uiState = uiState.copy(counter = uiState.counter + 1)
    }

}
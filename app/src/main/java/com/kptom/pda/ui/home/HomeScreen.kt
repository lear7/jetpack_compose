package com.kptom.pda.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kptom.pda.ui.AlertDialogDemo
import com.kptom.pda.ui.DropdownMenuDemo
import com.kptom.pda.ui.ModalBottomSheetLayoutDemo

@Composable
fun HomeScreen(action1: () -> Unit) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState = viewModel.uiState

    Column(Modifier.padding(10.dp)) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                action1()
            },
        ) {
            Text("Go to UserList")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.increase()
            },
        ) {
            Text("Counter: ${uiState.counter}")
        }
        AlertDialogDemo()
        DropdownMenuDemo()
        ModalBottomSheetLayoutDemo()
    }
}
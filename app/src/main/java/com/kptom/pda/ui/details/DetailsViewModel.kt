package com.kptom.pda.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kptom.pda.common.Argument
import com.kptom.pda.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val username: String? = savedStateHandle[Argument.USERNAME]
    var uiState by mutableStateOf(DetailsUiState())
        private set

    init {
        username?.let {
            viewModelScope.launch(Dispatchers.IO) {
                detailsRepository.refreshDetails(it)
                detailsRepository.getUserDetails(it).collect { detail ->
                    withContext(Dispatchers.Main) {
                        uiState = if (detail == null) {
                            uiState.copy(offline = true)
                        } else {
                            uiState.copy(
                                detail = detail,
                                offline = false
                            )
                        }
                    }
                }
            }
        }
    }

}
package com.lear.compose.ui.users

import com.lear.compose.domain.User

data class UsersUiState(
    val list: List<User> = listOf(),
    val offline: Boolean = false
)
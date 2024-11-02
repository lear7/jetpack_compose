package com.kptom.pda.ui.users

import com.kptom.pda.domain.User

data class UsersUiState(
    val list: List<User> = listOf(),
    val offline: Boolean = false
)
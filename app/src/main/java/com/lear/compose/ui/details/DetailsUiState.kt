package com.lear.compose.ui.details

import com.lear.compose.domain.Details
import com.lear.compose.utils.formatDate

data class DetailsUiState(
    val detail: Details = Details(),
    val offline: Boolean = false
) {
    val formattedUserSince = formatDate(detail.userSince)
}
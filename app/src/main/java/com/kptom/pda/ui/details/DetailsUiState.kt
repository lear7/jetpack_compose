package com.kptom.pda.ui.details

import com.kptom.pda.domain.Details
import com.kptom.pda.utils.formatDate

data class DetailsUiState(
    val detail: Details = Details(),
    val offline: Boolean = false
) {
    val formattedUserSince = formatDate(detail.userSince)
}
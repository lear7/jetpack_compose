package com.lear.compose.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lear.compose.domain.Details

@Entity
data class DetailsEntity constructor(
    @PrimaryKey
    val user: String,
    val avatar: String,
    val name: String,
    val userSince: String,
    val location: String
)

fun DetailsEntity.asDomainModel(): Details {
    return Details(
        user = user,
        avatar = avatar,
        name = name,
        userSince = userSince,
        location = location
    )
}
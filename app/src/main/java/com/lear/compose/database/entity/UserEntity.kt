package com.lear.compose.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lear.compose.domain.User

@Entity
data class UserEntity constructor(
    @PrimaryKey
    val id: Int,
    val avatar: String,
    val username: String
)

fun List<UserEntity>.asDomainModel(): List<User> {
    return map {
        User(
            id = it.id,
            avatar = it.avatar,
            username = it.username
        )
    }
}
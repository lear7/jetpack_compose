package com.lear.compose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lear.compose.database.dao.UsersDao
import com.lear.compose.database.entity.DetailsEntity
import com.lear.compose.database.entity.UserEntity

@Database(entities = [UserEntity::class, DetailsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
}
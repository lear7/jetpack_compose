package com.kptom.pda.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kptom.pda.database.dao.UsersDao
import com.kptom.pda.database.entity.DetailsEntity
import com.kptom.pda.database.entity.UserEntity

@Database(entities = [UserEntity::class, DetailsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
}
package com.lear.compose.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lear.compose.database.entity.DetailsEntity
import com.lear.compose.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("select * from UserEntity")
    fun getUsers(): Flow<List<UserEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserEntity>)

    @Query("select * from DetailsEntity WHERE user LIKE :user")
    fun getDetails(user: String): Flow<DetailsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(detailsEntity: DetailsEntity)
}
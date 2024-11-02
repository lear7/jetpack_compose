package com.kptom.pda.repository

import com.kptom.pda.database.AppDatabase
import com.kptom.pda.database.entity.asDomainModel
import com.kptom.pda.domain.User
import com.kptom.pda.network.UsersApi
import com.kptom.pda.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersApi: UsersApi,
    private val appDatabase: AppDatabase
) {

    val users: Flow<List<User>?> =
        appDatabase.usersDao.getUsers().map { it?.asDomainModel() }

    suspend fun refreshUsers() {
        try {
            val users = usersApi.getUsers()
            appDatabase.usersDao.insertUsers(users.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}
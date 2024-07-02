package com.amlavati.data_local.source

import com.amlavati.data.data_source.local.LocalUserDataSource
import com.amlavati.data_local.db.user.UserDao
import com.amlavati.data_local.db.user.UserEntity
import com.amlavati.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : LocalUserDataSource {
    override fun getUsers(): Flow<List<User>> =
        userDao.getUsers().map { userEntity ->
            userEntity.map {
                User(
                    id = it.id,
                    name = it.name,
                    userName = it.username,
                    email = it.email
                )
            }
        }


    override suspend fun addUsers(users: List<User>) {
        val entityUsers = users.map {
            UserEntity(it.id, it.name, it.userName, it.email)
        }
        userDao.insertUsers(entityUsers)
    }
}
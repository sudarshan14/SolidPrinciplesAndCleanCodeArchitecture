package com.amlavati.data.repository

import com.amlavati.data.data_source.local.LocalUserDataSource
import com.amlavati.data.data_source.remote.RemoteUserDataSource
import com.amlavati.domain.entity.User
import com.amlavati.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * User repository impl
 *
 * @property localUserDataSource
 * @property remoteUserDataSource
 * @constructor Create empty User repository impl
 */
class UserRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
    private val remoteUserDataSource: RemoteUserDataSource
) : UserRepository {

    override fun getUsers(): Flow<List<User>> {

        return remoteUserDataSource.getUsers().onEach {
            localUserDataSource.addUsers(it)
        }
    }

    override fun getUser(id: Long): Flow<User> =
        remoteUserDataSource.getUser(id).onEach {
            localUserDataSource.addUsers(listOf(it))
        }

}
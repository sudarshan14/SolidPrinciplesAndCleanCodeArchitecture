package com.amlavati.data_remote.source

import com.amlavati.data.data_source.remote.RemoteUserDataSource
import com.amlavati.data_remote.networking.user.UserApiModel
import com.amlavati.data_remote.networking.user.UserService
import com.amlavati.domain.entity.UseCaseException
import com.amlavati.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Remote user data source impl
 *
 * @property userService
 * @constructor Create empty Remote user data source impl
 */
class RemoteUserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : RemoteUserDataSource {
    override fun getUsers(): Flow<List<User>> = flow {
        emit(userService.getUsers())
    }.map { users ->
        users.map { userApiModel ->
            convert(userApiModel)
        }
    }.catch {
        throw UseCaseException.UserException(it)
    }

    override fun getUser(id: Long): Flow<User> = flow {
        emit(userService.getUser(id))
    }.map { user ->
        convert(user)
    }.catch {
        throw UseCaseException.UserException(it)
    }


    private fun convert(userApiModel: UserApiModel): User {
        return User(userApiModel.id, userApiModel.name, userApiModel.username, userApiModel.email)
    }
}
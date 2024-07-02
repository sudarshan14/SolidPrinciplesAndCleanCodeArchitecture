package com.amlavati.data.data_source.remote

import com.amlavati.domain.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * Remote user data source
 *
 * @constructor Create empty Remote user data source
 */
interface RemoteUserDataSource {

    /**
     * Get users
     *
     * @return
     */
    fun getUsers(): Flow<List<User>>

    /**
     * Get user
     *
     * @param id
     * @return
     */
    fun getUser(id: Long): Flow<User>
}
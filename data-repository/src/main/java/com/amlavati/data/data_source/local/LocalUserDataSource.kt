package com.amlavati.data.data_source.local

import com.amlavati.domain.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * Local user data source
 *
 * @constructor Create empty Local user data source
 */
interface LocalUserDataSource {

    /**
     * Get users
     *
     * @return
     */
    fun getUsers(): Flow<List<User>>

    /**
     * Add users
     *
     * @param users
     */
    suspend fun addUsers(users: List<User>)
}
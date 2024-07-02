package com.amlavati.domain.repository

import com.amlavati.domain.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * User repository
 *
 * @constructor Create empty User repository
 */
interface UserRepository {

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
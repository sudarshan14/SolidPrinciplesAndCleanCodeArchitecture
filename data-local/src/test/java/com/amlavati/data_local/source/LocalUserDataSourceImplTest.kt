package com.amlavati.data_local.source

import com.amlavati.data_local.db.user.UserDao
import com.amlavati.data_local.db.user.UserEntity
import com.amlavati.domain.entity.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalUserDataSourceImplTest {

    private val userDao: UserDao = mockk<UserDao>(relaxed = true)
    private val localUserDataSource = LocalUserDataSourceImpl(userDao)

    @Test
    fun `users are returned form room database`() = runTest {
        val users = listOf(UserEntity(1, "name", "username", "email"))

        coEvery {
            userDao.getUsers()
        } returns flowOf(users)

        val actualResult = localUserDataSource.getUsers().first()

        val expectedResults = listOf(User(1, "name", "username", "email"))

        assertEquals(expectedResults, actualResult)
    }

    @Test
    fun `user is added to room database`() = runTest {
        val usersDb = listOf(UserEntity(1, "name", "username", "email"))
        val users = listOf(User(1, "name", "username", "email"))

        localUserDataSource.addUsers(users)
        coVerify {
            userDao.insertUsers(usersDb)
        }

    }

}
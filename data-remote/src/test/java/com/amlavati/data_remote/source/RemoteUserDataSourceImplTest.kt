package com.amlavati.data_remote.source

import android.content.res.Resources.NotFoundException
import com.amlavati.data_remote.networking.user.UserApiModel
import com.amlavati.data_remote.networking.user.UserService
import com.amlavati.domain.entity.UseCaseException
import com.amlavati.domain.entity.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses

class RemoteUserDataSourceImplTest {

    private val userService: UserService = mockk<UserService>()
    private val remoteUserDataSourceImpl = RemoteUserDataSourceImpl(userService)

    @Test
    fun `get users from remote service`() = runTest {

        val user1 = UserApiModel(1, "name1", "username1", "email1")
        val user2 = UserApiModel(2, "name1", "username1", "email1")
        val remoteUsers = listOf(user1, user2)

        coEvery {
            userService.getUsers()
        } returns remoteUsers

        val expected =
            listOf(User(1, "name1", "username1", "email1"), User(2, "name1", "username1", "email1"))

        val actual = remoteUserDataSourceImpl.getUsers().first()

        assertEquals(expected, actual)
    }

    @Test
    fun `get user from remote service`() = runTest {
        val id = 1L
        val remoteUser = UserApiModel(1, "name1", "username1", "email1")


        coEvery {
            userService.getUser(id)
        } returns remoteUser

        val expected = User(1, "name1", "username1", "email1")

        val actual = remoteUserDataSourceImpl.getUser(id).first()

        assertEquals(expected, actual)
    }


    @Test
    fun `getUsers api throws error`() = runTest {

        coEvery {
            userService.getUsers()
        } throws RuntimeException()

        remoteUserDataSourceImpl.getUsers().catch {
            assertTrue(it is UseCaseException.UserException)
        }.collect()

    }

    @Test
    fun `getUser api throws error`() = runTest {

        coEvery {
            userService.getUser(1L)
        } throws NotFoundException()

        remoteUserDataSourceImpl.getUser(1L).catch {
            assertTrue(it is UseCaseException.UserException)
        }.collect()

    }

}
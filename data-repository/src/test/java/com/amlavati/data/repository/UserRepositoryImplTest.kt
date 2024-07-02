package com.amlavati.data.repository

import com.amlavati.data.data_source.local.LocalUserDataSource
import com.amlavati.data.data_source.remote.RemoteUserDataSource
import com.amlavati.domain.entity.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class UserRepositoryImplTest {

    private val localUserDataSource = mockk<LocalUserDataSource>(relaxed = true)
    private val remoteUserDataSource = mockk<RemoteUserDataSource>()
    private val repository = UserRepositoryImpl(localUserDataSource, remoteUserDataSource)

    private val remoteUserDataSource1 = mock<RemoteUserDataSource>()
    private val localUserDataSource1 = mock<LocalUserDataSource>()
    private val repositoryImpl = UserRepositoryImpl(localUserDataSource1, remoteUserDataSource1)

    @Test
    fun `users are fetched from remote and saved locally`() = runTest {
//Arrange
        val user1 = User(1, "fn1", "ln1", "email1")
        val user2 = User(2, "fn2", "ln2", "email2")
        val users = listOf(user1, user2)

        coEvery {
            remoteUserDataSource.getUsers()
        } returns flowOf(users)
//Act
        val actual = repository.getUsers().first()

        coVerify { localUserDataSource.addUsers(users) }
//Assert
        assertEquals(actual, users)
    }

    @Test
    fun `users are fetched from remote and saved locally using mockito`() = runBlocking {
        val users = listOf(User(1, "name", "username", "email"))
        Mockito.`when`(remoteUserDataSource1.getUsers()).thenReturn(flowOf(users))
        val result = repositoryImpl.getUsers().first()
        verify(localUserDataSource1).addUsers(users)
        assertEquals(users, result)
    }


    @Test
    fun `user is fetched from remote and saved locally`() = runTest{

        //Arrange
        val user = User(1, "fn1", "ln1", "email1")
        coEvery {
            remoteUserDataSource.getUser(1)
        } returns flowOf(user)

        //Act
        val actual = repository.getUser(1).first()
        coVerify {
            localUserDataSource.addUsers(listOf(user))
        }

        //assert
        assertEquals(actual, user)

    }
}
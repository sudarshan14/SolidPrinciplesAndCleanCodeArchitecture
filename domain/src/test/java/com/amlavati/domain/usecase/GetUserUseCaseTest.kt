package com.amlavati.domain.usecase

import com.amlavati.domain.entity.User
import com.amlavati.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserUseCaseTest {
    private val userRepository = mockk<UserRepository>()
    private val useCase = GetUserUseCase(mockk(), userRepository)


    @Test
    fun `Get User When Passed User Id`() = runTest {

        val user = User(1L, "name", "userName", "email")

        coEvery {
            userRepository.getUser(1L)
        } returns flowOf(user)

        val result = useCase.process(GetUserUseCase.Request(1L)).first()
        val expectedResult = GetUserUseCase.Response(user)

        assertEquals(expectedResult, result)
    }


}
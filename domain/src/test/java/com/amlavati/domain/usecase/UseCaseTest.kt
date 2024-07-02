package com.amlavati.domain.usecase

import com.amlavati.domain.entity.Result
import com.amlavati.domain.entity.UseCaseException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class UseCaseTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    private val configuration = UseCase.Configuration(UnconfinedTestDispatcher())

    private val request = mockk<UseCase.Request>()
    private val response = mockk<UseCase.Response>()

    private lateinit var useCase: UseCase<UseCase.Request, UseCase.Response>

    @Before
    fun setUp() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flowOf(response)
            }
        }
    }

    @Test
    fun testExecuteSuccess() = runTest {
        val result = useCase.execute(request).first()
        assertEquals(Result.Success(response), result)
    }

    @Test
    fun `Test ExecuteUserException`() {
        val exception =
            UseCaseException.UserException(Throwable("Post Not Found"))

        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw exception
                }
            }
        }

        runTest {
            val result = useCase.execute(request).first()
            assertTrue((result as Result.Error).exception is UseCaseException.UserException)
        }
    }

    @Test
    fun `Test ExecutePostException`() {
        val exception =
            UseCaseException.PostException(Throwable("Post Not Found"))

        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw exception
                }
            }
        }

        runTest {
            val result = useCase.execute(request).first()
            assertTrue((result as Result.Error).exception is UseCaseException.PostException)
        }
    }

    @Test
    fun `Test ExecuteUnknownException`() {
        val exception =
            Throwable()

        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw exception
                }
            }
        }

        runTest {
            val result = useCase.execute(request).first()
            assertTrue((result as Result.Error).exception is UseCaseException.UnknownException)
        }
    }

}
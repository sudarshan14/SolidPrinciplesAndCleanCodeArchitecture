package com.amlavati.domain.usecase

import com.amlavati.domain.entity.Post
import com.amlavati.domain.repository.PostRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test

class GetPostUseCaseTest {


    private val postRepository = mockk<PostRepository>()
    private val useCase = GetPostUseCase(mockk(), postRepository)

    @Test
    fun `Get Post When Passed Post Id`() = runTest {

        val post = Post(1L, 1L, "title", "body")

        coEvery {
            postRepository.getPost(1L)
        } returns flowOf(post)

        val actualResult = useCase.process(GetPostUseCase.Request(1L)).first()

        val expectedResult = GetPostUseCase.Response(post)
        assertEquals(expectedResult, actualResult)

    }
}
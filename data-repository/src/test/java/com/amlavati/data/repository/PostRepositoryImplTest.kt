package com.amlavati.data.repository

import com.amlavati.data.data_source.local.LocalPostDataSource
import com.amlavati.data.data_source.remote.RemotePostDataSource
import com.amlavati.domain.entity.Post
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PostRepositoryImplTest {

    private val localPostDataSource = mockk<LocalPostDataSource>(relaxed = true)
    private val remotePostDataSource = mockk<RemotePostDataSource>()
    private val postRepository = PostRepositoryImpl(localPostDataSource, remotePostDataSource)

    @Test
    fun `getPosts fetches from remote and saves to local`() = runBlocking {
        // Given
        val posts = listOf(Post(1L, 1L, "post1", "post body1"), Post(2L, 2L, "post2", "post body2"))
        coEvery { remotePostDataSource.getPosts() } returns flowOf(posts)

        // When
        val actual = postRepository.getPosts().first() // Trigger flow collection

        // Then
//        coVerify { remotePostDataSource.getPosts() }
        coVerify { localPostDataSource.addPosts(posts) }

        assertEquals(actual, posts)
    }

    @Test
    fun `getPost fetches from remote and saves to local`() = runBlocking {
        // Given
        val postId = 1L
        val post = Post(postId, 1L, "post1", "body1")
        coEvery { remotePostDataSource.getPost(postId) } returns flowOf(post)

        // When
       val actual = postRepository.getPost(postId).first() // Trigger flow collection

        // Then

        coVerify { localPostDataSource.addPosts(listOf(post)) }

        assertEquals(actual,post)
    }
}

// Simple Post data class for the test
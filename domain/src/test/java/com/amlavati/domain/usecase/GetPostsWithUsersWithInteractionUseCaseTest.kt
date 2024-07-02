package com.amlavati.domain.usecase

import com.amlavati.domain.entity.Interaction
import com.amlavati.domain.entity.Post
import com.amlavati.domain.entity.PostWithUser
import com.amlavati.domain.entity.User
import com.amlavati.domain.repository.InteractionRepository
import com.amlavati.domain.repository.PostRepository
import com.amlavati.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test


class GetPostsWithUsersWithInteractionUseCaseTest {

    private val postRepository = mockk<PostRepository>()
    private val userRepository = mockk<UserRepository>()
    private val interactionRepository = mockk<InteractionRepository>()

    private val useCase = GetPostsWithUsersWithInteractionUseCase(
        mockk(),
        postRepository,
        userRepository,
        interactionRepository
    )

    @Test
    fun testProcess() = runTest {

        val user1 = User(1L, "name1", "user1", "email1")
        val user2 = User(2L, "name2", "user2", "email2")

        val post1 = Post(1L, user1.id, "title1", "body1")
        val post2 = Post(2L, user1.id, "title2", "body2")
        val post3 = Post(3L, user2.id, "title3", "body3")
        val post4 = Post(4L, user2.id, "title4", "body4")

        val interaction = Interaction(10)

        coEvery {
            userRepository.getUsers()
        } returns flowOf(listOf(user1, user2))

        coEvery {
            postRepository.getPosts()
        } returns flowOf(listOf(post1, post2, post3, post4))

        coEvery {
            interactionRepository.getInteraction()
        } returns flowOf(interaction)

        val result = useCase.process(GetPostsWithUsersWithInteractionUseCase.Request).first()

        val expectedPostUsers = listOf(
            PostWithUser(post1, user1),
            PostWithUser(post2, user1),
            PostWithUser(post3, user2),
            PostWithUser(post4, user2)
        )
        val expectedResult =
            GetPostsWithUsersWithInteractionUseCase.Response(expectedPostUsers, interaction)

        assertEquals(expectedResult, result)

    }

}
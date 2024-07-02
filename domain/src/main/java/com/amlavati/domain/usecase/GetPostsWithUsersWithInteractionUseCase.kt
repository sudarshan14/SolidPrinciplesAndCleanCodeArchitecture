package com.amlavati.domain.usecase

import com.amlavati.domain.entity.Interaction
import com.amlavati.domain.entity.PostWithUser
import com.amlavati.domain.repository.InteractionRepository
import com.amlavati.domain.repository.PostRepository
import com.amlavati.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * Get posts with users with interaction use case
 *
 * @property postRepository
 * @property userRepository
 * @property interactionRepository
 * @constructor
 *
 * @param configuration
 */
class GetPostsWithUsersWithInteractionUseCase @Inject constructor(
    configuration: Configuration,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val interactionRepository: InteractionRepository
) : UseCase<GetPostsWithUsersWithInteractionUseCase.Request, GetPostsWithUsersWithInteractionUseCase.Response>(
    configuration
) {

    override fun process(request: Request): Flow<Response> =
        combine(
            postRepository.getPosts(),
            userRepository.getUsers(),
            interactionRepository.getInteraction()
        ) { posts, users, interaction ->
            val postUsers = posts.map { post ->
                val user = users.first {
                    it.id == post.userId
                }
                PostWithUser(post, user)
            }
            Response(postUsers, interaction)
        }

    /**
     * Response
     *
     * @property postUsers
     * @property interaction
     * @constructor Create empty Response
     */
    data class Response(val postUsers: List<PostWithUser>, val interaction: Interaction) :
        UseCase.Response

    object Request : UseCase.Request
}


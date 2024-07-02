package com.amlavati.domain.usecase

import com.amlavati.domain.entity.Post
import com.amlavati.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Get post use case
 *
 * @property postRepository
 * @constructor
 *
 * @param configuration
 */

class GetPostUseCase @Inject constructor(
    configuration: Configuration,
    private val postRepository: PostRepository
) : UseCase<GetPostUseCase.Request, GetPostUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        postRepository.getPost(request.postId).map {
            Response(it)

        }

    /**
     * Request
     *
     * @property postId
     * @constructor Create empty Request
     */
    data class Request(val postId: Long) : UseCase.Request

    /**
     * Response
     *
     * @property post
     * @constructor Create empty Response
     */
    data class Response(val post: Post) : UseCase.Response


}
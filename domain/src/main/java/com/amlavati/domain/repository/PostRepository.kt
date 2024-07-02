package com.amlavati.domain.repository

import com.amlavati.domain.entity.Post
import kotlinx.coroutines.flow.Flow

/**
 * Post repository
 *
 * @constructor Create empty Post repository
 */
interface PostRepository {
    /**
     * Get posts
     *
     * @return
     */
    fun getPosts(): Flow<List<Post>>

    /**
     * Get post
     *
     * @param id
     * @return
     */
    fun getPost(id: Long): Flow<Post>
}
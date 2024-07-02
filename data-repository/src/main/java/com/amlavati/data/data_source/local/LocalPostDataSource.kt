package com.amlavati.data.data_source.local

import com.amlavati.domain.entity.Post
import kotlinx.coroutines.flow.Flow

/**
 * Local post data source
 *
 * @constructor Create empty Local post data source
 */
interface LocalPostDataSource {

    /**
     * Get posts
     *
     * @return
     */
    fun getPosts(): Flow<List<Post>>

    /**
     * Add posts
     *
     * @param posts
     */
    suspend fun addPosts(posts: List<Post>)

}
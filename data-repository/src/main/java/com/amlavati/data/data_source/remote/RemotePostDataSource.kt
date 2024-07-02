package com.amlavati.data.data_source.remote

import com.amlavati.domain.entity.Post
import kotlinx.coroutines.flow.Flow

/**
 * Remote post data source
 *
 * @constructor Create empty Remote post data source
 */
interface RemotePostDataSource {

    /**
     * Get posts
     *
     * @return
     */
    fun getPosts(): Flow<List<Post>>

    /**
     * Get post of a particular user
     *
     * @param id
     * @return
     */
    fun getPost(id: Long): Flow<Post>

}
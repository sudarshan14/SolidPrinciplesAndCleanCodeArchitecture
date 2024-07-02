package com.amlavati.data_local.source

import com.amlavati.data.data_source.local.LocalPostDataSource
import com.amlavati.data_local.db.post.PostDao
import com.amlavati.data_local.db.post.PostEntity
import com.amlavati.domain.entity.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPostDataSourceImpl @Inject constructor(
    private val postDao: PostDao
) : LocalPostDataSource {
    override fun getPosts(): Flow<List<Post>> =
        postDao.getPosts().map { postEntity ->
            postEntity.map {
                Post(
                    id = it.id,
                    userId = it.userId,
                    title = it.title,
                    body = it.body
                )
            }
        }

    override suspend fun addPosts(posts: List<Post>) {
        postDao.insertPosts(posts.map {
            PostEntity(
                id = it.id,
                userId = it.userId,
                title = it.title,
                body = it.body
            )
        })
    }
}
package com.amlavati.data.repository

import com.amlavati.data.data_source.local.LocalPostDataSource
import com.amlavati.data.data_source.remote.RemotePostDataSource
import com.amlavati.domain.entity.Post
import com.amlavati.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val localPostDataSource: LocalPostDataSource,
    private val remotePostDataSource: RemotePostDataSource
) : PostRepository {
    override fun getPosts(): Flow<List<Post>> =
        remotePostDataSource.getPosts().onEach {
            localPostDataSource.addPosts(it)
        }


    override fun getPost(id: Long): Flow<Post> =
        remotePostDataSource.getPost(id).onEach {
            localPostDataSource.addPosts(listOf(it))
        }
}
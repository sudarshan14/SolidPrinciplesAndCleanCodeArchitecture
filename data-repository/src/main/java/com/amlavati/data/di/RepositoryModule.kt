package com.amlavati.data.di

import com.amlavati.data.repository.InteractionRepositoryImpl
import com.amlavati.data.repository.PostRepositoryImpl
import com.amlavati.data.repository.UserRepositoryImpl
import com.amlavati.domain.repository.InteractionRepository
import com.amlavati.domain.repository.PostRepository
import com.amlavati.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindInteractionRepository(interactionRepositoryImpl: InteractionRepositoryImpl): InteractionRepository

    @Binds
    abstract fun bindPostRepository(postRepository: PostRepositoryImpl): PostRepository

}
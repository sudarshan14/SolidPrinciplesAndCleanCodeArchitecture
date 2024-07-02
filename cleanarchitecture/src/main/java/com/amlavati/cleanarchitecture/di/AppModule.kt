package com.amlavati.cleanarchitecture.di

import com.amlavati.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideUseCaseConfiguration()=
        UseCase.Configuration(Dispatchers.IO)
}
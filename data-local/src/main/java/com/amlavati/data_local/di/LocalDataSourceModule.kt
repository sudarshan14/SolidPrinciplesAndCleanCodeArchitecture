package com.amlavati.data_local.di

import com.amlavati.data.data_source.local.LocalInteractionDataSource
import com.amlavati.data.data_source.local.LocalPostDataSource
import com.amlavati.data.data_source.local.LocalUserDataSource
import com.amlavati.data_local.source.LocalInteractionDataSourceImpl
import com.amlavati.data_local.source.LocalPostDataSourceImpl
import com.amlavati.data_local.source.LocalUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindPostDataSource(lostDataSourceImpl: LocalPostDataSourceImpl):
            LocalPostDataSource

    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: LocalUserDataSourceImpl):
            LocalUserDataSource

    @Binds
    abstract fun bindInteractionDataStore(interactionDataStore: LocalInteractionDataSourceImpl): LocalInteractionDataSource
}
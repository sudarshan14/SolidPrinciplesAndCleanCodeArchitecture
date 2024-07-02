package com.amlavati.data_remote.di

import com.amlavati.data.data_source.remote.RemotePostDataSource
import com.amlavati.data.data_source.remote.RemoteUserDataSource
import com.amlavati.data_remote.source.RemotePostDataSourceImpl
import com.amlavati.data_remote.source.RemoteUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Remote data source module
 * we use Hilt to bind the implementations from this module with the
 * abstractions defined in the data-repository module.
 * @constructor Create empty Remote data source module
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    /**
     * Bind post remote data source
     *
     * @param postRemoteDataSource
     * @return
     */
    @Binds
    abstract fun bindPostRemoteDataSource(postRemoteDataSource: RemotePostDataSourceImpl): RemotePostDataSource


    /**
     * Bind user remote data source
     *
     * @param userRemoteDataSource
     * @return
     */
    @Binds
    abstract fun bindUserRemoteDataSource(userRemoteDataSource: RemoteUserDataSourceImpl): RemoteUserDataSource

}
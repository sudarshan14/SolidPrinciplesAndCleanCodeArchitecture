package com.amlavati.data_local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room

import com.amlavati.data_local.db.AppDatabase
import com.amlavati.data_local.db.post.PostDao
import com.amlavati.data_local.db.user.UserDao
import com.amlavati.data_local.source.LocalInteractionDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_preferences")

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "my-dtabase")
            .build()


    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun providesPostDao(appDatabase: AppDatabase): PostDao = appDatabase.postDao()

    @Provides
    fun provideLocalInteractionDataSourceImpl(@ApplicationContext context: Context) =
        LocalInteractionDataSourceImpl(context.dataStore)

}
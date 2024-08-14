package com.arvind.pardypandataskcomposeapp.di

import android.content.Context
import androidx.room.Room
import com.arvind.pardypandataskcomposeapp.data.local.PhotoDao
import com.arvind.pardypandataskcomposeapp.data.local.PhotosDatabase
import com.arvind.pardypandataskcomposeapp.data.repository.LocalDataSourceImpl
import com.arvind.pardypandataskcomposeapp.domain.localdatasource.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PhotosDatabase {
        return Room.databaseBuilder(context, PhotosDatabase::class.java, "photos.db").build()
    }

    @Provides
    @Singleton
    fun providePhotoDao(database: PhotosDatabase): PhotoDao {
        return database.photoDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(photoDao: PhotoDao): LocalDataSource {
        return LocalDataSourceImpl(photoDao)
    }
}

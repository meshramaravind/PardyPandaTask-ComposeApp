package com.arvind.pardypandataskcomposeapp.di

import com.arvind.pardypandataskcomposeapp.data.repository.Repository
import com.arvind.pardypandataskcomposeapp.domain.localdatasource.LocalDataSource
import com.arvind.pardypandataskcomposeapp.domain.repository.RemoteDataSource
import com.arvind.pardypandataskcomposeapp.domain.usecase.GetPhotosListUseCase
import com.arvind.pardypandataskcomposeapp.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesUseCases(repository: Repository): UseCases {
        return UseCases(
            getPhotosListUseCase = GetPhotosListUseCase(repository),
        )
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object RepositoryModule {

        @Provides
        @Singleton
        fun providePhotoRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): Repository {
            return Repository(remoteDataSource, localDataSource)
        }
    }
}
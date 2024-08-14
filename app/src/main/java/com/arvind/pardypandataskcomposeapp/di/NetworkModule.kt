package com.arvind.pardypandataskcomposeapp.di


import com.arvind.pardypandataskcomposeapp.data.remote.PhotosAPI
import com.arvind.pardypandataskcomposeapp.data.repository.RemoteDataSourceImpl
import com.arvind.pardypandataskcomposeapp.domain.repository.RemoteDataSource
import com.arvind.pardypandataskcomposeapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(okHttpClient: OkHttpClient): PhotosAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PhotosAPI::class.java)
    }


    @Provides
    @Singleton
    fun providesRemoteDataSource(
        photosAPI: PhotosAPI,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            photosAPI = photosAPI
        )
    }
}
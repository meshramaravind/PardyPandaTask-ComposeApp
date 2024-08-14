package com.arvind.pardypandataskcomposeapp.data.repository


import com.arvind.pardypandataskcomposeapp.data.local.PhotoDao
import com.arvind.pardypandataskcomposeapp.domain.localdatasource.LocalDataSource
import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse
import com.arvind.pardypandataskcomposeapp.domain.repository.RemoteDataSource
import com.arvind.pardypandataskcomposeapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
//    suspend fun getPhotosData(): Resource<List<PhotosDataResponse>> {
//        return remote.getPhotosData()
//    }

    suspend fun getPhotos(): Resource<List<PhotosDataResponse>> {
        return try {
            val photos = remote.getPhotosData()
            CoroutineScope(Dispatchers.IO).launch {
                localDataSource.savePhotos(photos)
            }
            Resource.Success(photos)
        } catch (e: Exception) {
            runBlocking {
                val cachedPhotos = localDataSource.getPhotos()
                if (cachedPhotos.isNotEmpty()) {
                    Resource.Success(cachedPhotos)
                } else {
                    Resource.Error("An error occurred", null)
                }
            }
        }
    }
}
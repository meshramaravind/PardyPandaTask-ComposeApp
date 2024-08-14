package com.arvind.pardypandataskcomposeapp.data.repository

import com.arvind.pardypandataskcomposeapp.data.remote.PhotosAPI
import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse
import com.arvind.pardypandataskcomposeapp.domain.repository.RemoteDataSource
import com.arvind.pardypandataskcomposeapp.utils.Resource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val photosAPI: PhotosAPI
) : RemoteDataSource {
    override suspend fun getPhotosData(): List<PhotosDataResponse> {
        return photosAPI.getPhotosData()
    }
}

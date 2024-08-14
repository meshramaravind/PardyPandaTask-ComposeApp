package com.arvind.pardypandataskcomposeapp.data.repository

import com.arvind.pardypandataskcomposeapp.data.local.PhotoDao
import com.arvind.pardypandataskcomposeapp.domain.localdatasource.LocalDataSource
import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val photoDao: PhotoDao
) : LocalDataSource {
    override suspend fun getPhotos(): List<PhotosDataResponse> {
        return photoDao.getPhotos()
    }

    override suspend fun savePhotos(photos: List<PhotosDataResponse>) {
        photoDao.insertAll(photos)
    }
}

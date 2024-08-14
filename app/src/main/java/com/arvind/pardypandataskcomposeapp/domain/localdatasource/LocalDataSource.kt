package com.arvind.pardypandataskcomposeapp.domain.localdatasource

import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse

interface LocalDataSource {
    suspend fun getPhotos(): List<PhotosDataResponse>
    suspend fun savePhotos(photos: List<PhotosDataResponse>)
}
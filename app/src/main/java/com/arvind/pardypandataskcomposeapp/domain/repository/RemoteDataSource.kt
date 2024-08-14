package com.arvind.pardypandataskcomposeapp.domain.repository

import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse
import com.arvind.pardypandataskcomposeapp.utils.Resource

interface RemoteDataSource {
    suspend fun getPhotosData(): List<PhotosDataResponse>
}
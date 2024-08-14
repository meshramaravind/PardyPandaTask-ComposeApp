package com.arvind.pardypandataskcomposeapp.data.remote

import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse
import retrofit2.http.GET

interface PhotosAPI {
    @GET("photos")
    suspend fun getPhotosData(): List<PhotosDataResponse>
}
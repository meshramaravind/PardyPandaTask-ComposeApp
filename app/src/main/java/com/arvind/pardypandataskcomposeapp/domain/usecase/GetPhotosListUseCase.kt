package com.arvind.pardypandataskcomposeapp.domain.usecase

import com.arvind.pardypandataskcomposeapp.data.repository.Repository
import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse
import com.arvind.pardypandataskcomposeapp.utils.Resource
import javax.inject.Inject

class GetPhotosListUseCase @Inject constructor(private val repository: Repository) {
//    suspend operator fun invoke(): Resource<List<PhotosDataResponse>> {
//        return repository.getPhotosData()
//    }

    suspend fun execute(): Resource<List<PhotosDataResponse>> {
        return repository.getPhotos()
    }
}
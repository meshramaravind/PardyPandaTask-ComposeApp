package com.arvind.pardypandataskcomposeapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse
import com.arvind.pardypandataskcomposeapp.domain.usecase.GetPhotosListUseCase
import com.arvind.pardypandataskcomposeapp.domain.usecase.UseCases
import com.arvind.pardypandataskcomposeapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val useCase: GetPhotosListUseCase
) : ViewModel() {
//    suspend fun getPhotosData(): Resource<List<PhotosDataResponse>> {
//        val result = useCase.getPhotosListUseCase()
//        Timber.d(result.data.toString())
//        return result
//    }


    private val _photos = mutableStateOf<Resource<List<PhotosDataResponse>>>(Resource.Loading())
    val photosData: State<Resource<List<PhotosDataResponse>>> get() = _photos

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _photos.value = useCase.execute()
        }
    }
}
package com.arvind.pardypandataskcomposeapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse
import com.arvind.pardypandataskcomposeapp.screens.component.PhotosInfo
import com.arvind.pardypandataskcomposeapp.utils.Resource
import com.arvind.pardypandataskcomposeapp.viewmodel.PhotosViewModel

@Composable
fun HomeScreen(viewModel: PhotosViewModel = hiltViewModel()) {

    //    Box(modifier = Modifier.fillMaxSize()) {
//        if (details is Resource.Success) {
//            PhotosInfo(
//                details = details
//            )
//        } else {
//            CircularProgressBar()
//        }
//    }

    when (val details = viewModel.photosData.value) {
        is Resource.Loading -> {
            CircularProgressBar()
        }

        is Resource.Success -> {
            val photos = details.data ?: emptyList()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(photos) { photo ->
                    PhotosInfo(photo)
                }
            }

        }

        is Resource.Error -> {
            Text(text = (details as Resource.Error).message ?: "An error occurred")
        }
    }
}


@Composable
fun CircularProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

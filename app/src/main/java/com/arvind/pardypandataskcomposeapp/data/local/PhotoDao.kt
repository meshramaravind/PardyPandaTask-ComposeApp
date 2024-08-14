package com.arvind.pardypandataskcomposeapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse
import com.arvind.pardypandataskcomposeapp.utils.Resource

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photos")
    suspend fun getPhotos(): List<PhotosDataResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<PhotosDataResponse>)
}
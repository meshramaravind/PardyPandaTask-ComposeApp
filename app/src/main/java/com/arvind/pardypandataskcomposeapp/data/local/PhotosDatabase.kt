package com.arvind.pardypandataskcomposeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arvind.pardypandataskcomposeapp.domain.models.PhotosDataResponse

@Database(entities = [PhotosDataResponse::class], version = 1)
abstract class PhotosDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}
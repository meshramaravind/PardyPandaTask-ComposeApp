package com.arvind.pardypandataskcomposeapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
data class PhotosDataResponse(
    @PrimaryKey val id: Int,
    @SerializedName("albumId") var albumId: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("thumbnailUrl") var thumbnailUrl: String? = null,
)
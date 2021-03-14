package com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "photos_table")
data class PhotoEntity(
    @PrimaryKey
    val phot_id:String,
    var created_at:String?,
    var width:Int?,
    var height:Int?,
    var color:String?,
    var blur_hash:String?,
    var description:String?,
    var photo_regular:String?,
    var photo_small:String?,
    var photo_thumb:String?,
    var views:Int?,
    var download: Int?)
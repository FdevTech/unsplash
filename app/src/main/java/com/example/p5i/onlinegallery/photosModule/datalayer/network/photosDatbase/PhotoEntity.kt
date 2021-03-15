package com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.p5i.onlinegallery.Util.POJs.PhotoPOJ
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain


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




fun List<PhotoEntity>.asDomainModel():List<PhotoDomain>{

    return this.map {
        PhotoDomain(
            blur_hash = it.blur_hash,
            photo_regular =it.photo_regular,
            photo_small = it.photo_small,
            photo_thumb = it.photo_thumb,
            download = it.download,
            views = it.views
        )
    }
}


fun List<PhotoPOJ>.asDatabasePhotoModel():List<PhotoEntity>{

    return this.map {
        PhotoEntity(
            phot_id = it.id,
            created_at = it.created_at,
            width = it.width,
            height = it.height,
            color = it.color,
            blur_hash = it.blur_hash,
            description = "${it.description} ${it.alt_description}",
            photo_regular = it.urls.regular,
            photo_small = it.urls.small,
            photo_thumb = it.urls.thumb,
            views = it.views,
            download = it.download

        )
    }
}
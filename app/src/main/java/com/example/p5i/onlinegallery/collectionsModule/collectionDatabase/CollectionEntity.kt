package com.example.p5i.onlinegallery.collectionsModule.collectionDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.p5i.onlinegallery.Util.POJs.CollectionPOJ
import com.example.p5i.onlinegallery.collectionsModule.datlayer.domainedata.CollectionDomain

@Entity(tableName = "collection_table")
data class CollectionEntity(

    @PrimaryKey
    val id:String,
    val title:String?,
    val published_at:String?,
    val total_photos :Int?,

    val cover_photo_id:String?,
    val cover_photo_blur_hash:String?,
    val cover_photo_regular:String?,
    val cover_photo_small:String?,
    val cover_photo_thumb:String?,

    val first_photo_id:String?,
    val first_photo_blur_hash:String?,
    val first_photo_regular:String?,
    val first_photo_small:String?,
    val first_photo_thumb:String? ,

    val second_photo_id:String?,
    val second_photo_blur_hash:String?,
    val second_photo_regular:String?,
    val second_photo_small:String?,
    val second_photo_thumb:String?,

    val third_photo_id:String?,
    val third_photo_blur_hash:String?,
    val third_photo_regular:String?,
    val third_photo_small:String?,
    val third_photo_thumb:String?
)
fun List<CollectionPOJ>.asCollectionEntity():List<CollectionEntity>
{
    return this.map {
        CollectionEntity(
            id=it.id,
            published_at = it.published_at,
            title = it.title,
            total_photos = it.total_photos,
            cover_photo_id = it.cover_photo.id,
            cover_photo_blur_hash = it.cover_photo.blur_hash,
            cover_photo_regular = it.cover_photo.urls.regular,
            cover_photo_small = it.cover_photo.urls.small,
            cover_photo_thumb = it.cover_photo.urls.thumb,
            first_photo_id = it.preview_photos.get(0).id,
            first_photo_blur_hash = it.preview_photos.get(0).blur_hash,
            first_photo_regular = it.preview_photos.get(0).urls.regular,
            first_photo_small = it.preview_photos.get(0).urls.small,
            first_photo_thumb = it.preview_photos.get(0).urls.thumb,
            second_photo_id = it.preview_photos.get(1).id,
            second_photo_blur_hash = it.preview_photos.get(1).blur_hash,
            second_photo_regular = it.preview_photos.get(1).urls.regular,
            second_photo_small = it.preview_photos.get(1).urls.small,
            second_photo_thumb = it.preview_photos.get(1).urls.thumb,
            third_photo_id= it.preview_photos.get(2).id,
            third_photo_blur_hash = it.preview_photos.get(2).blur_hash,
            third_photo_regular = it.preview_photos.get(2).urls.regular,
            third_photo_small = it.preview_photos.get(2).urls.small,
            third_photo_thumb = it.preview_photos.get(2).urls.thumb

        )
    }
}

fun List<CollectionEntity>.asCollectionDomain():List<CollectionDomain>
{
    return this.map {
        CollectionDomain(
            id=it.id,
            title = it.title,
            total_photos = it.total_photos,
            cover_photo_id = it.cover_photo_id,
            cover_photo_thumb = it.cover_photo_thumb,
            cover_photo_small = it.cover_photo_small,
            cover_photo_regular = it.cover_photo_regular,
            cover_photo_blur_hash = it.cover_photo_blur_hash,
            first_photo_thumb = it.first_photo_thumb,
            first_photo_small = it.first_photo_small,
            first_photo_regular =it.first_photo_regular,
            first_photo_blur_hash = it.first_photo_blur_hash,
            first_photo_id = it.first_photo_id,
            second_photo_id = it.second_photo_id,
            second_photo_blur_hash = it.second_photo_blur_hash,
            second_photo_regular = it.second_photo_regular,
            second_photo_small = it.second_photo_small,
            second_photo_thumb = it.second_photo_thumb,
            third_photo_thumb = it.third_photo_thumb,
            third_photo_small = it.third_photo_small,
            third_photo_regular = it.third_photo_regular,
            third_photo_blur_hash = it.third_photo_blur_hash,
            third_photo_id = it.third_photo_id
        )
    }
}
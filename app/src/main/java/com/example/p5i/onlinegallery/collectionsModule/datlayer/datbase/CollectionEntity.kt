package com.example.p5i.onlinegallery.collectionsModule.datlayer.datbase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collection")
data class CollectionEntity(
    @PrimaryKey
    val id:String,
    val title:String?,
    val description:String?,
    val published_at:String?,
    val last_collected_at:String?,
    val updated_at:String?,
    val total_photos:String?,
    val private:String?,
    val cover_photo_id:String?,
    val cover_photo_width:String?,
    val cover_photo_height:String?,
    val cover_photo_blur_hash:String?,
    val cover_photo_description:String?,
    val cover_photo_alt_description:String?,
    val cover_photo_regular:String?,
    val cover_photo_small:String?,
    val cover_photo_thumb:String?,
    val preview_photos_first_photo_id:String?,
    val preview_photos_first_photo_blur_hash:String?,
    val preview_photos_first_photo_regular:String?,
    val preview_photos_first_photo_small:String?,
    val preview_photos_first_photo_thumb:String?,
    val preview_photos_second_photo_id:String?,
    val preview_photos_second_photo_blur_hash:String?,
    val preview_photos_second_photo_regular:String?,
    val preview_photos_second_photo_small:String?,
    val preview_photos_second_photo_thumb:String?,
    val preview_photos_third_photo_id:String?,
    val preview_photos_third_photo_blur_hash:String?,
    val preview_photos_third_photo_regular:String?,
    val preview_photos_third_photo_small:String?,
    val preview_photos_third_photo_thumb:String?

)
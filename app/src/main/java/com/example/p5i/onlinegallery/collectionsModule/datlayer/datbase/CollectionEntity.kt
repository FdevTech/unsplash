package com.example.p5i.onlinegallery.collectionsModule.datlayer.datbase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collection")
data class CollectionEntity(
    @PrimaryKey
    var id:String,
    var title:String?,
    var description:String?,
    var published_at:String?,
    var last_collected_at:String?,
    var updated_at:String?,
    var total_photos:String?,
    var private:String?,
    var cover_photo_id:String?,
    var cover_photo_width:String?,
    var cover_photo_height:String?,
    var cover_photo_blur_hash:String?,
    var cover_photo_description:String?,
    var cover_photo_alt_description:String?,
    var cover_photo_regular:String?,
    var cover_photo_small:String?,
    var cover_photo_thumb:String?,
    var preview_photos_first_photo_id:String?,
    var preview_photos_first_photo_blur_hash:String?,
    var preview_photos_first_photo_regular:String?,
    var preview_photos_first_photo_small:String?,
    var preview_photos_first_photo_thumb:String?,
    var preview_photos_second_photo_id:String?,
    var preview_photos_second_photo_blur_hash:String?,
    var preview_photos_second_photo_regular:String?,
    var preview_photos_second_photo_small:String?,
    var preview_photos_second_photo_thumb:String?,
    var preview_photos_third_photo_id:String?,
    var preview_photos_third_photo_blur_hash:String?,
    var preview_photos_third_photo_regular:String?,
    var preview_photos_third_photo_small:String?,
    var preview_photos_third_photo_thumb:String?

)

//TODO converting from network to this class methods
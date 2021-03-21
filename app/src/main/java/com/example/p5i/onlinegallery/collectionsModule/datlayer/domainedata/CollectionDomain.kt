package com.example.p5i.onlinegallery.collectionsModule.datlayer.domainedata

data class CollectionDomain(

    val id:String,
    val title:String?,
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
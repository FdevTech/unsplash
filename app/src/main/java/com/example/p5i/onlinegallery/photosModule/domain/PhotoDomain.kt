package com.example.p5i.onlinegallery.photosModule.domain

data class PhotoDomain(
    val id:String,
    var blur_hash:String?,
    var photo_regular:String?,
    var photo_small:String?,
    var photo_thumb:String?,
    var views:Int?,
    var download: Int?,
    val liked_by_user:Boolean?,
    val likes:Int,
    val user_name:String?,
    val user_first_name:String?,
    val user_last_name:String?,
    val user_profile_image_small:String?,
    val user_profile_image_medium:String?,
    val user_profile_large:String?
)
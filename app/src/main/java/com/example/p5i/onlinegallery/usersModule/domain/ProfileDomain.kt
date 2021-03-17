package com.example.p5i.onlinegallery.usersModule.domain

data class ProfileDomain
    (
    val id:String,
    val username:String?,
    val bio:String?,
    val twitter_username:String?,
    val instagram_username:String?,
    val total_collections:Int?,
    val total_likes:Int?,
    val total_photos:Int?,
    val location:String?,
    val profile_image_small:String?,
    val profile_image_medium:String?,
    val profile_image_large:String?
)
package com.example.p5i.onlinegallery.usersModule.dtaLayer.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profile")
data class ProfileEntity (
    @PrimaryKey
    var id:String?,
    var username:String?,
    var name:String?,
    var first_name:String?,
    var last_name:String?,
    var twitter_username:String?,
    var instagram_username:String?,
    var bio:String?,
    var location:String?,
    var profile_image_small:String?,
    var profile_image_medium:String?,
    var profile_image_large:String?,
    var total_collections:Long?,
    var total_likes:Long?,
    var total_photos:Long?,
    var for_hire:Boolean?

){
}
package com.example.p5i.onlinegallery.usersModule.datalayer.databse

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.p5i.onlinegallery.Util.POJs.UserPOJ
import com.example.p5i.onlinegallery.usersModule.domain.ProfileDomain

@Entity(tableName = "profile_table")
data class ProfileEntity
    (
    @PrimaryKey
    val id:String,
    val username:String?,
    val name:String?,
    val first_name:String?,
    val last_name:String?,
    val twitter_username:String?,
    val instagram_username:String?,
    val total_collections:Int?,
    val total_likes:Int?,
    val total_photos:Int?,
    val bio:String?,
    val location:String?,
    val profile_image_small:String?,
    val profile_image_medium:String?,
    val profile_image_large:String?
)
@Entity(tableName = "photographer_profile_table")
data class PhotographerProfileEntity
    (
    @PrimaryKey
    val id:String,
    val username:String?,
    val name:String?,
    val first_name:String?,
    val last_name:String?,
    val twitter_username:String?,
    val instagram_username:String?,
    val total_collections:Int?,
    val total_likes:Int?,
    val total_photos:Int?,
    val bio:String?,
    val location:String?,
    val profile_image_small:String?,
    val profile_image_medium:String?,
    val profile_image_large:String?
)


fun UserPOJ.asPhotographerProfileEntity():PhotographerProfileEntity
{
    return PhotographerProfileEntity(
            id=this.id,
            username = this.username,
            name=this.name,
            first_name = this.first_name,
            last_name = this.last_name,
            twitter_username = this.twitter_username,
            instagram_username = this.instagram_username,
            total_collections = this.total_collections,
            total_likes = this.total_likes,
            total_photos = this.total_photos,
            bio = this.bio,
            location = this.location,
            profile_image_large = this.profile_image.large,
            profile_image_medium = this.profile_image.medium,
            profile_image_small = this.profile_image.small

        )

}
fun UserPOJ.asProfileEntity():ProfileEntity
{
    return ProfileEntity(
        id=this.id,
        username = this.username,
        name=this.name,
        first_name = this.first_name,
        last_name = this.last_name,
        twitter_username = this.twitter_username,
        instagram_username = this.instagram_username,
        total_collections = this.total_collections,
        total_likes = this.total_likes,
        total_photos = this.total_photos,
        bio = this.bio,
        location = this.location,
        profile_image_large = this.profile_image.large,
        profile_image_medium = this.profile_image.medium,
        profile_image_small = this.profile_image.small

    )

}
fun ProfileEntity.asProfileDomain():ProfileDomain
{
    return ProfileDomain(
            id=this.id,
            username = this.username,
            bio=this.bio,
            twitter_username = this.username,
            instagram_username = this.instagram_username,
            total_photos = this.total_photos,
            total_collections = this.total_collections,
            total_likes = this.total_likes,
            location = this.location,
            profile_image_small = this.profile_image_small,
            profile_image_medium = this.profile_image_medium,
            profile_image_large = this.profile_image_large
        )

}
fun PhotographerProfileEntity.asPhotographerProfileDomain():ProfileDomain
{
    return ProfileDomain(
        id=this.id,
        username = this.username,
        bio=this.bio,
        twitter_username = this.username,
        instagram_username = this.instagram_username,
        total_photos = this.total_photos,
        total_collections = this.total_collections,
        total_likes = this.total_likes,
        location = this.location,
        profile_image_small = this.profile_image_small,
        profile_image_medium = this.profile_image_medium,
        profile_image_large = this.profile_image_large
    )

}
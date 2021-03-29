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
@Entity(tableName = "user_photos_table")
data class UserPhotoEntity(
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
@Entity(tableName = "user_liked_photos_table")
data class UserLikedPhotoEntity(
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

@Entity(tableName = "photos_collection_table")
data class PhotoEntityCollection(
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

@Entity(tableName = "photos_topics_table")
data class PhotoEntityTopics(
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

fun List<PhotoEntity>.asDomainModel():List<PhotoDomain>{

    return this.map {
        PhotoDomain(
            blur_hash = it.blur_hash,
            photo_regular =it.photo_regular,
            photo_small = it.photo_small,
            photo_thumb = it.photo_thumb,
            download = it.download,
            views = it.views,
            id = it.phot_id,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user_name,
            user_first_name = it.user_first_name,
            user_last_name = it.user_last_name,
            user_profile_image_medium = it.user_profile_image_medium,
            user_profile_image_small = it.user_profile_image_small,
            user_profile_large = it.user_profile_large
        )
    }
}
fun List<UserPhotoEntity>.asUserPhotoDomainModel():List<PhotoDomain>{

    return this.map {
        PhotoDomain(
            blur_hash = it.blur_hash,
            photo_regular =it.photo_regular,
            photo_small = it.photo_small,
            photo_thumb = it.photo_thumb,
            download = it.download,
            views = it.views,
            id = it.phot_id,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user_name,
            user_first_name = it.user_first_name,
            user_last_name = it.user_last_name,
            user_profile_image_medium = it.user_profile_image_medium,
            user_profile_image_small = it.user_profile_image_small,
            user_profile_large = it.user_profile_large
        )
    }
}
fun List<UserLikedPhotoEntity>.asUserLikedPhotoDomainModel():List<PhotoDomain>{

    return this.map {
        PhotoDomain(
            blur_hash = it.blur_hash,
            photo_regular =it.photo_regular,
            photo_small = it.photo_small,
            photo_thumb = it.photo_thumb,
            download = it.download,
            views = it.views,
            id = it.phot_id,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user_name,
            user_first_name = it.user_first_name,
            user_last_name = it.user_last_name,
            user_profile_image_medium = it.user_profile_image_medium,
            user_profile_image_small = it.user_profile_image_small,
            user_profile_large = it.user_profile_large
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
            download = it.download,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user.name,
            user_first_name = it.user.first_name,
            user_last_name = it.user.last_name,
            user_profile_image_small = it.user.profile_image.small,
            user_profile_image_medium = it.user.profile_image.medium,
            user_profile_large = it.user.profile_image.large

        )
    }
}
fun List<PhotoPOJ>.asDatabaseUserPhotoModel():List<UserPhotoEntity>{

    return this.map {
        UserPhotoEntity(
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
            download = it.download,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user.name,
            user_first_name = it.user.first_name,
            user_last_name = it.user.last_name,
            user_profile_image_small = it.user.profile_image.small,
            user_profile_image_medium = it.user.profile_image.medium,
            user_profile_large = it.user.profile_image.large

        )
    }
}
fun List<PhotoPOJ>.asDatabaseUserLikedPhotoModel():List<UserLikedPhotoEntity>{

    return this.map {
        UserLikedPhotoEntity(
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
            download = it.download,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user.name,
            user_first_name = it.user.first_name,
            user_last_name = it.user.last_name,
            user_profile_image_small = it.user.profile_image.small,
            user_profile_image_medium = it.user.profile_image.medium,
            user_profile_large = it.user.profile_image.large

        )
    }
}
fun List<PhotoPOJ>.asDatabasePhotTopicoModel():List<PhotoEntityTopics>{

    return this.map {
        PhotoEntityTopics(
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
            download = it.download,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user.name,
            user_first_name = it.user.first_name,
            user_last_name = it.user.last_name,
            user_profile_image_small = it.user.profile_image.small,
            user_profile_image_medium = it.user.profile_image.medium,
            user_profile_large = it.user.profile_image.large

        )
    }
}
fun List<PhotoEntityTopics>.asDomainModelFromTopicPhoto():List<PhotoDomain>{

    return this.map {
        PhotoDomain(
            blur_hash = it.blur_hash,
            photo_regular =it.photo_regular,
            photo_small = it.photo_small,
            photo_thumb = it.photo_thumb,
            download = it.download,
            views = it.views,
            id = it.phot_id,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user_name,
            user_first_name = it.user_first_name,
            user_last_name = it.user_last_name,
            user_profile_image_medium = it.user_profile_image_medium,
            user_profile_image_small = it.user_profile_image_small,
            user_profile_large = it.user_profile_large
        )
    }
}

fun List<PhotoPOJ>.asDatabasePhotCollectionModel():List<PhotoEntityCollection>{

    return this.map {
        PhotoEntityCollection(
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
            download = it.download,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user.name,
            user_first_name = it.user.first_name,
            user_last_name = it.user.last_name,
            user_profile_image_small = it.user.profile_image.small,
            user_profile_image_medium = it.user.profile_image.medium,
            user_profile_large = it.user.profile_image.large

        )
    }
}
fun List<PhotoEntityCollection>.asDomainModelFromCollectionPhoto():List<PhotoDomain>{

    return this.map {
        PhotoDomain(
            blur_hash = it.blur_hash,
            photo_regular =it.photo_regular,
            photo_small = it.photo_small,
            photo_thumb = it.photo_thumb,
            download = it.download,
            views = it.views,
            id = it.phot_id,
            liked_by_user = it.liked_by_user,
            likes = it.likes,
            user_name = it.user_name,
            user_first_name = it.user_first_name,
            user_last_name = it.user_last_name,
            user_profile_image_medium = it.user_profile_image_medium,
            user_profile_image_small = it.user_profile_image_small,
            user_profile_large = it.user_profile_large
        )
    }
}


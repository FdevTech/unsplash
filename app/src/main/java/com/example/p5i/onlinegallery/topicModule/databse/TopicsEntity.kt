package com.example.p5i.onlinegallery.topicModule.databse

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.p5i.onlinegallery.Util.POJs.TopicsPOJ
import com.example.p5i.onlinegallery.topicModule.doamin.TopicsDomain

@Entity(tableName = "topics_table")
data class TopicsEntity(
    @PrimaryKey
    val id: String,
    val title:String?,
    val description:String?,
    val starts_at:String?,
    val published_at:String?,
    val updated_at:String?,
    val ends_at:String?,
    val total_photos:Int?,
    val cover_photo_id:String?,
    val cover_photo_blur_hash:String?,
    val cover_photo_regular:String?,
    val cover_photo_small:String?,
    val cover_photo_thumb:String?,
    val slug:String?


)

fun List<TopicsPOJ>.asTopicsEntity():List<TopicsEntity>
{
    return this.map {
        TopicsEntity(
            id=it.id,
            title = it.title,
            description = it.description,
            starts_at = it.starts_at,
            published_at = it.published_at,
            updated_at = it.updated_at,
            ends_at = it.ends_at,
            total_photos = it.total_photos,
            cover_photo_id = it.cover_photo.id,
            cover_photo_blur_hash = it.cover_photo.blur_hash,
            cover_photo_regular = it.cover_photo.urls.regular,
            cover_photo_small = it.cover_photo.urls.small,
            cover_photo_thumb = it.cover_photo.urls.thumb,
            slug = it.slug

        )
    }
}

fun List<TopicsEntity>.astopicsDomain():List<TopicsDomain>
{
    return this.map {
        TopicsDomain(
            id = it.id,
            title = it.title,
            description = it.description,
            published_at = it.published_at,
            updated_at = it.updated_at,
            total_photos = it.total_photos,
            cover_photo_blur_hash = it.cover_photo_blur_hash,
            cover_photo_regular = it.cover_photo_regular,
            cover_photo_thumb = it.cover_photo_thumb,
            cover_photo_small = it.cover_photo_small,
            slug = it.slug
        )
    }
}
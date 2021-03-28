package com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PhotosDao
{

    @Query("update photos_table set liked_by_user = :liked_by_user where phot_id=:photo_id ")
    suspend fun insertLikedByUser(photo_id:String,liked_by_user:Boolean)
    @Query("select liked_by_user from photos_table where phot_id=:photo_id")
    suspend fun chekLike(photo_id:String):Boolean
    @Query("select * from photos_table")
    fun getAllPhotos():LiveData<List<PhotoEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserOrUpdatePhotos(photos:List<PhotoEntity>)


    @Query("update photos_collection_table set liked_by_user = :liked_by_user where phot_id=:photo_id ")
    suspend fun insertLikedByUserInCollectionPhoto(photo_id:String,liked_by_user:Boolean)
    @Query("select * from photos_collection_table")
    fun getAllPhotosFromCollection():LiveData<List<PhotoEntityCollection>>
    @Insert()
    suspend fun inserOrUpdatePhotosCollection(photos:List<PhotoEntityCollection>)
    @Query("delete from photos_collection_table")
    fun clearPhotosCollection()
    @Query("select liked_by_user from photos_collection_table where phot_id=:photo_id")
    suspend fun chekLikePhotoClolection(photo_id:String):Boolean


    @Query("update photos_topics_table set liked_by_user = :liked_by_user where phot_id=:photo_id ")
    suspend fun insertLikedByUserInTopicPhoto(photo_id:String,liked_by_user:Boolean)
    @Query("select * from photos_topics_table")
    fun getAllPhotosFromTopic():LiveData<List<PhotoEntityTopics>>
    @Insert()
    suspend fun inserOrUpdatePhotosTopics(photos:List<PhotoEntityTopics>)
    @Query("delete from photos_topics_table")
    fun clearPhotosTopics()
    @Query("select liked_by_user from photos_table where phot_id=:photo_id")
    suspend fun chekLikePhotoTopic(photo_id:String):Boolean
}

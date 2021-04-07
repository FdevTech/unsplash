package com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PhotosDao
{

    @Query("update photos_table set liked_by_user = :liked_by_user where phot_id=:photo_id ")
    suspend fun insertLikedByUser(photo_id:String,liked_by_user:Boolean)

    @Query("update photos_table set likes = :numberOfLikes where phot_id=:photo_id ")
    suspend fun addOrRemoveLiked(photo_id:String,numberOfLikes:Int)



    //this method is just used to test


    @Query("select liked_by_user from photos_table where phot_id=:photo_id")
    suspend fun chekLike(photo_id:String):Boolean
    @Query("select likes from photos_table where phot_id=:photo_id")
    suspend fun cheknumberOflikes(photo_id:String):Int

    @Query("select liked_by_user from photos_collection_table where phot_id=:photo_id")
    suspend fun chekLikefromCollectiontable(photo_id:String):Boolean
    @Query("select likes from photos_collection_table where phot_id=:photo_id")
    suspend fun cheknumberOflikesFromCollection(photo_id:String):Int
    @Query("update photos_collection_table set likes = :numberOfLikes where phot_id=:photo_id ")
    suspend fun addOrRemoveLikedFromCollection(photo_id:String,numberOfLikes:Int)

    @Query("select * from photos_table")
    fun getAllPhotos():LiveData<List<PhotoEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserOrUpdatePhotos(photos:List<PhotoEntity>)
    @Query("delete from photos_table")
    fun clearPhotos()

    @Query("select * from user_photos_table")
    fun getAllUserPhotos():LiveData<List<UserPhotoEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserOrUpdateUserPhotos(photos:List<UserPhotoEntity>)
    @Query("delete from user_photos_table")
    fun clearUserPhotos()

    @Query("select * from user_liked_photos_table")
    fun getAllUserLikedPhotos():LiveData<List<UserLikedPhotoEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserOrUpdateUserLikedPhotos(photos:List<UserLikedPhotoEntity>)
    @Query("delete from user_liked_photos_table")
    fun clearUserLikedPhotosC()

    @Query("delete from user_liked_photos_table where phot_id=:photo_id")
    fun deltePhotofromlikedTable(photo_id:String)

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

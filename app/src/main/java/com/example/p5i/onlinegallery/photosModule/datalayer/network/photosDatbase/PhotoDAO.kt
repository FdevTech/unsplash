package com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PhotosDao
{
    @Query("select * from photos_table")
    fun getAllPhotos():LiveData<List<PhotoEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserOrUpdatePhotos(photos:List<PhotoEntity>)

    @Query("select * from photos_collection_table")
    fun getAllPhotosFromCollection():LiveData<List<PhotoEntityCollection>>
    @Insert()
    suspend fun inserOrUpdatePhotosCollection(photos:List<PhotoEntityCollection>)
    @Query("delete from photos_collection_table")
    fun clearPhotosCollection()

    @Query("select * from photos_topics_table")
    fun getAllPhotosFromTopic():LiveData<List<PhotoEntityTopics>>
    @Insert()
    suspend fun inserOrUpdatePhotosTopics(photos:List<PhotoEntityTopics>)
    @Query("delete from photos_topics_table")
    fun clearPhotosTopics()
}

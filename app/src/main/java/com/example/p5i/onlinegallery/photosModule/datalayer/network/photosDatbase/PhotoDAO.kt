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
}

package com.example.p5i.onlinegallery.photosModule.datalayer.datbase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PhotoDAO
{
    @Query("select*from photosTable")
    suspend fun getAllPhotos():LiveData<List<PhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos:List<PhotoEntity>)
}
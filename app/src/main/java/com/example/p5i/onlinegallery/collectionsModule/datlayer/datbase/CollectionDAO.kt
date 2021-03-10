package com.example.p5i.onlinegallery.collectionsModule.datlayer.datbase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CollectionDAO
{
   @Query("select * from COLLECTION")
   suspend fun getCollection():LiveData<List<CollectionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(collection:List<CollectionEntity>)



}
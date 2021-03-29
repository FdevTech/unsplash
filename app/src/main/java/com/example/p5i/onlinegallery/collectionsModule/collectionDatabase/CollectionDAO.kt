package com.example.p5i.onlinegallery.collectionsModule.collectionDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CollectionDAO
{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrUpdateCollection(collection:List<CollectionEntity>)

    @Query("select * from collection_table")
    fun getAllinCollection():LiveData<List<CollectionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUserCollection(collection:List<UserCollectionEntity>)

    @Query("select * from `user-collection_table`")
    fun getAllinUserCollection():LiveData<List<UserCollectionEntity>>
}
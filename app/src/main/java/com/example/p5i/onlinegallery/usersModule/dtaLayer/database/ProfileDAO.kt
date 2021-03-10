package com.example.p5i.onlinegallery.usersModule.dtaLayer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDAO
{
    @Query("select * from Profile")
    suspend fun getMyProfile():LiveData<ProfileEntity>

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insert(profile:ProfileEntity)
}
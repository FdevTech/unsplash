package com.example.p5i.onlinegallery.usersModule.datalayer.databse

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON

@Dao
interface ProfileDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(data:ProfileEntity)

    @Query("select * from profile_table")
    fun getProfileData():LiveData<ProfileEntity>
}

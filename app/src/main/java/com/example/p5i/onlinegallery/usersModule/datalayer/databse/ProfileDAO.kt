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

    @Query("select username from profile_table ")
    fun getUserName():LiveData<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdatePhotographerProfile(data:PhotographerProfileEntity)

    @Query("select * from photographer_profile_table")
    fun getPhotographerProfileData():LiveData<PhotographerProfileEntity>

    @Query("delete from photographer_profile_table")
    fun clearphtographertable()
}

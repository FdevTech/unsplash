package com.example.p5i.onlinegallery.topicModule.databse

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.p5i.onlinegallery.topicModule.databse.TopicsEntity

@Dao
interface TopicsDAO {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(data:List<TopicsEntity>)

    @Query("select * from topics_table")
    fun gettopics():LiveData<List<TopicsEntity>>
}
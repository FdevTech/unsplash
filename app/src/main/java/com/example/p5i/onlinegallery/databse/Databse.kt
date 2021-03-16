package com.example.p5i.onlinegallery.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.p5i.onlinegallery.collectionsModule.collectionDatabase.CollectionDAO
import com.example.p5i.onlinegallery.collectionsModule.collectionDatabase.CollectionEntity
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.PhotoEntity
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.PhotosDao
import com.example.p5i.onlinegallery.topicModule.databse.TopicsEntity
import com.example.p5i.onlinegallery.topicModule.databse.TopicsDAO


@Database(entities = [PhotoEntity::class,CollectionEntity::class,TopicsEntity::class], version = 6, exportSchema = false)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract val photosDao: PhotosDao
    abstract val topicsDAO: TopicsDAO
    abstract val collectionDao:CollectionDAO

}

private lateinit var INSTANCE:UnsplashDatabase

fun getDatabse(context: Context):UnsplashDatabase
{
    synchronized(UnsplashDatabase::class.java)
    {
        if(!::INSTANCE.isInitialized)
        {
           INSTANCE= Room.databaseBuilder(
                context,
               UnsplashDatabase::class.java,
               "UnsplashDatabase"
           )   .fallbackToDestructiveMigration()
               .build()
        }
    }
    return INSTANCE
}
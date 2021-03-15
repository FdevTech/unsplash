package com.example.p5i.onlinegallery.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.PhotoEntity
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.PhotosDao



@Database(entities = [PhotoEntity::class], version = 3, exportSchema = false)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract val photosDao: PhotosDao

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
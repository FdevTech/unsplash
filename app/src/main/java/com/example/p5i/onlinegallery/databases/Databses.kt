package com.example.p5i.onlinegallery.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.p5i.onlinegallery.collectionsModule.datlayer.datbase.CollectionDAO
import com.example.p5i.onlinegallery.collectionsModule.datlayer.datbase.CollectionEntity
import com.example.p5i.onlinegallery.photosModule.datalayer.datbase.PhotoDAO
import com.example.p5i.onlinegallery.photosModule.datalayer.datbase.PhotoEntity
import com.example.p5i.onlinegallery.usersModule.dtaLayer.database.ProfileDAO
import com.example.p5i.onlinegallery.usersModule.dtaLayer.database.ProfileEntity

@Database(entities = [PhotoEntity::class,CollectionEntity::class,ProfileEntity::class],version = 1,exportSchema = false)
abstract class UnsplashCachedDatabase:RoomDatabase()
{
    abstract val collectionDAO:CollectionDAO
    abstract val photoDAO:PhotoDAO
    abstract val profileDAO:ProfileDAO
}
private lateinit var INSTANCE:UnsplashCachedDatabase

fun getUnsplashCachedDatabase(context: Context):UnsplashCachedDatabase{
    synchronized(UnsplashCachedDatabase::class.java)
    {
        if(!::INSTANCE.isInitialized)
        {
            INSTANCE= Room.databaseBuilder(context
                      ,UnsplashCachedDatabase::class.java,
                           "UnsplashCachedDatabase").build()
        }
    }
    return INSTANCE
}
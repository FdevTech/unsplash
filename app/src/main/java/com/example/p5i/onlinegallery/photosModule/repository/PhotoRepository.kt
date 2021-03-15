package com.example.p5i.onlinegallery.photosModule.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDatabasePhotoModel
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDomainModel
import com.example.p5i.onlinegallery.topicModule.networkLayer.TopicsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "PhotoRepository"
class PhotoRepository(private val unsplashDatabase: UnsplashDatabase,private val credentials:String)
{
    val photos=Transformations.map(unsplashDatabase.photosDao.getAllPhotos(),{
        it.asDomainModel()
    })

    suspend fun referchPhotos()
    {
        Log.d(TAG, "referchPhotos: ")
        withContext(Dispatchers.IO)
        {
            val value= TopicsAPI.topics.getPhotosFromTopics(credentials)
            Log.d(TAG, "referchPhotos: ${value.size}")
            unsplashDatabase.photosDao.inserOrUpdatePhotos(value.asDatabasePhotoModel())
        }
    }
}

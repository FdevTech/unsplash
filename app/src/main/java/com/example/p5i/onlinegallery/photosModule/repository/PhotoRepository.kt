package com.example.p5i.onlinegallery.photosModule.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.collectionsModule.datlayer.network.CollectionAPI
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.photosModule.datalayer.network.Photos
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

private const val TAG = "PhotoRepository"
class PhotoRepository(private val unsplashDatabase: UnsplashDatabase,private val credentials:String)
{
    val photos=Transformations.map(unsplashDatabase.photosDao.getAllPhotos(),{
        it.asDomainModel()
    })
    val photosTopic=Transformations.map(unsplashDatabase.photosDao.getAllPhotosFromTopic(),{
        it.asDomainModelFromTopicPhoto()
    })
    val photosCollection=Transformations.map(unsplashDatabase.photosDao.getAllPhotosFromCollection(),{
        it.asDomainModelFromCollectionPhoto()
    })

    suspend fun referchPhotos()
    {
        Log.d(TAG, "referchPhotos: ")
        var i:Int=1
        withContext(Dispatchers.IO)
        {
            var photosList= Photos.PhotosAPI.photos.getPhotos(credentials).body()
            val xtotal= Photos.PhotosAPI.photos.getPhotos(credentials).headers().get("X-Total")
            Log.d(TAG, "referchPhotos: xtotal: $xtotal")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            if(photosList!=null)
            {
                while(i.compareTo(pages!!)<=0)
                {
                    try {
                        photosList= Photos.PhotosAPI.photos.getPhotos(credentials,page = i).body()
                        //Log.d(TAG, "referchPhotos: ${photosList?.size}")
                        unsplashDatabase.photosDao.inserOrUpdatePhotos(photosList?.asDatabasePhotoModel()!!)
                    }catch (exception:Exception)
                    {
                        Log.d(TAG, "referchPhotos: ${exception.message}")
                    }
                    i++
                    Log.d(TAG, "referchPhotos: $i")
                }
            }


        }
    }
    suspend fun retrivePhotoFromTopicsToTest(topicName:String)
    {
        Log.d(TAG, "retrivePhotoFromTopics: ")

        var i:Int=1
        withContext(Dispatchers.IO)
        {
            unsplashDatabase.photosDao.clearPhotosTopics()
            var photosList= Photos.PhotosAPI.photos.getPhotoFromTopic(credentials,topicName = topicName).body()
            val xtotal= Photos.PhotosAPI.photos.getPhotos(credentials).headers().get("X-Total")
            Log.d(TAG, "retrivePhotoFromTopics: xtotal: $xtotal")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            Log.d(TAG, "retrivePhotoFromTopics: $pages")
            try {
                photosList= Photos.PhotosAPI.photos.getPhotoFromTopic(credentials,topicName = topicName).body()
                //Log.d(TAG, "referchPhotos: ${photosList?.size}")
                unsplashDatabase.photosDao.inserOrUpdatePhotosTopics(photosList?.asDatabasePhotTopicoModel()!!)
            }catch (exception:Exception)
            {
                Log.d(TAG, "retrivePhotoFromTopics: ${exception.message}")
            }

        }
    }

    suspend fun retrivePhotoFromCollectionToTest(collectionID:String)
    {
        Log.d(TAG, "retrivePhotoFromCollectionToTest: ")

        var i:Int=1
        withContext(Dispatchers.IO)
        {
            unsplashDatabase.photosDao.clearPhotosCollection()
            var photosList= Photos.PhotosAPI.photos.getPhotosFromCollection(credentials,collectionID = collectionID).body()
            val xtotal= Photos.PhotosAPI.photos.getPhotos(credentials).headers().get("X-Total")
            Log.d(TAG, "retrivePhotoFromCollectionToTest: xtotal: $xtotal")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            Log.d(TAG, "retrivePhotoFromCollectionToTest: $pages")
            try {
                photosList= Photos.PhotosAPI.photos.getPhotosFromCollection(credentials,collectionID = collectionID).body()
                //Log.d(TAG, "referchPhotos: ${photosList?.size}")
                unsplashDatabase.photosDao.inserOrUpdatePhotosCollection(photosList?.asDatabasePhotCollectionModel()!!)
            }catch (exception:Exception)
            {
                Log.d(TAG, "retrivePhotoFromCollectionToTest: ${exception.message}")
            }

        }
    }
    suspend fun retrivePhotoFromTopics(collectionID:String)
    {
        Log.d(TAG, "retrivePhotoFromTopics: ")

        var i:Int=1
        withContext(Dispatchers.IO)
        {
            unsplashDatabase.photosDao.clearPhotosCollection()
            var photosList= Photos.PhotosAPI.photos.getPhotosFromCollection(credentials,collectionID = collectionID).body()
            val xtotal= Photos.PhotosAPI.photos.getPhotos(credentials).headers().get("X-Total")
            Log.d(TAG, "retrivePhotoFromTopics: xtotal: $xtotal")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            Log.d(TAG, "retrivePhotoFromTopics: $pages")
             if(photosList!=null)
             {
                 while(i.compareTo(pages!!)<=0)
                 {
                     try {
                         photosList= Photos.PhotosAPI.photos.getPhotosFromCollection(credentials,collectionID = collectionID).body()
                         //Log.d(TAG, "referchPhotos: ${photosList?.size}")
                         unsplashDatabase.photosDao.inserOrUpdatePhotosCollection(photosList?.asDatabasePhotCollectionModel()!!)
                     }catch (exception:Exception)
                     {
                         Log.d(TAG, "retrivePhotoFromTopics: ${exception.message}")
                     }
                     i++
                     Log.d(TAG, "retrivePhotoFromTopics: $i")
                 }
             }


        }
    }
}

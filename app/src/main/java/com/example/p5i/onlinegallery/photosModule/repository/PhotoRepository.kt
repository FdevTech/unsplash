package com.example.p5i.onlinegallery.photosModule.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.collectionsModule.datlayer.network.CollectionAPI
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.photosModule.datalayer.network.Photos
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.*
import com.google.android.material.snackbar.Snackbar
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
    val userPhotos=Transformations.map(unsplashDatabase.photosDao.getAllUserPhotos()){
        it.asUserPhotoDomainModel()
    }
    val userLikedPhotos=Transformations.map(unsplashDatabase.photosDao.getAllUserLikedPhotos()){
        it.asUserLikedPhotoDomainModel()
    }



    suspend fun like_unlikePhoto(photo_id:String)
    {
        withContext(Dispatchers.IO)
        {
            val chekLikedByUserOrNo=unsplashDatabase.photosDao.chekLike(photo_id)
            Log.d(TAG, "like_unlikePhoto: $chekLikedByUserOrNo")
            //todo we should send a post request to the server before updating the database
            unsplashDatabase.photosDao.insertLikedByUser(photo_id,!chekLikedByUserOrNo)
        }
    }


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
    suspend fun referchPhotosToTest()
    {
        Log.d(TAG, "referchPhotosToTest: ")
        var i:Int=1
        withContext(Dispatchers.IO)
        {
            val response=Photos.PhotosAPI.photos.getPhotos(credentials)
            var photosList= response.body()
            val xtotal= response.headers().get("X-Total")
            Log.d(TAG, "referchPhotosToTest: xtotal: $xtotal")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            if(photosList!=null)
            {
                try {
                   // photosList= Photos.PhotosAPI.photos.getPhotos(credentials).body()
                    //Log.d(TAG, "referchPhotos: ${photosList?.size}")
                    unsplashDatabase.photosDao.inserOrUpdatePhotos(photosList?.asDatabasePhotoModel()!!)
                }catch (exception:Exception)
                {
                    Log.d(TAG, "referchPhotosToTest: ${exception.message}")


                }
            }


        }
    }
    suspend fun retrivePhotoFromTopicsToTest(topicName:String)
    {
        Log.d(TAG, "retrivePhotoFromTopicsToTest: $topicName")

        var i:Int=1
        withContext(Dispatchers.IO)
        {
            unsplashDatabase.photosDao.clearPhotosTopics()
            val response=Photos.PhotosAPI.photos.getPhotoFromTopic(credentials,topicName = topicName)
            var photosList= response.body()
            val xtotal= Photos.PhotosAPI.photos.getPhotos(credentials).headers().get("X-Total")
            Log.d(TAG, "retrivePhotoFromTopicsToTest: xtotal: $xtotal")
            Log.d(TAG, "retrivePhotoFromTopicsToTest: body ${response.body()?.size}")
            Log.d(TAG, "retrivePhotoFromTopicsToTest: response errorBody ${response.errorBody()}")
            Log.d(TAG, "retrivePhotoFromTopicsToTest: response code ${response.code()}")
            try {
                //photosList= Photos.PhotosAPI.photos.getPhotoFromTopic(credentials,topicName = topicName).body()
                Log.d(TAG, "retrivePhotoFromTopicsToTest size: ${photosList?.size}")
                unsplashDatabase.photosDao.inserOrUpdatePhotosTopics(photosList?.asDatabasePhotTopicoModel()!!)
            }catch (exception:Exception)
            {
                Log.d(TAG, "retrivePhotoFromTopicsToTest: ${exception.message}")
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
            Log.d(TAG, "retrivePhotoFromCollectionToTest: pages $pages")
            try {
                //photosList= Photos.PhotosAPI.photos.getPhotosFromCollection(credentials,collectionID = collectionID).body()
                //Log.d(TAG, "referchPhotos: ${photosList?.size}")
                Log.d(TAG, "retrivePhotoFromCollectionToTest: size ${photosList?.size}")
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
    suspend fun retriveUserPhotosToTest(user:String)
    {
        Log.d(TAG, "retriveUserPhotosToTest: ")

        var i:Int=1
        withContext(Dispatchers.IO)
        {
            //unsplashDatabase.photosDao.clearUserPhotosCollection()
            val response=Photos.PhotosAPI.photos.getUserPhotos(credentials,username = "curta")
            var photosList= response.body()
            val xtotal= response.headers().get("X-Total")
            Log.d(TAG, "retriveUserPhotosToTest: xtotal: $xtotal")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            Log.d(TAG, "retriveUserPhotosToTest: pages $pages")
            if(photosList!=null)
            {
                try {

                    Log.d(TAG, "retriveUserPhotosToTest: size ${photosList?.size}")
                    unsplashDatabase.photosDao.inserOrUpdateUserPhotos(photosList?.asDatabaseUserPhotoModel()!!)
                }catch (exception:Exception)
                {
                    Log.d(TAG, "retriveUserPhotosToTest: ${exception.message}")
                }
            }

        }
    }
    suspend fun retriveUserLikedPhotosToTest(user:String)
    {
        Log.d(TAG, "retriveUserLikedPhotosToTest: ")

        var i:Int=1
        withContext(Dispatchers.IO)
        {
           // unsplashDatabase.photosDao.clearUserPhotosCollection()
            val response=Photos.PhotosAPI.photos.getUserLikedPhotos(credentials,username = "curta")
            var photosList= response.body()
            val xtotal= response.headers().get("X-Total")
            Log.d(TAG, "retriveUserLikedPhotosToTest: xtotal: $xtotal")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            Log.d(TAG, "retriveUserLikedPhotosToTest: pages $pages")
            Log.d(TAG, "retriveUserLikedPhotosToTest: code ${response.code()}")
            if(photosList!=null)
            {
                try {

                    Log.d(TAG, "retriveUserLikedPhotosToTest: size ${photosList?.size}")
                    unsplashDatabase.photosDao.inserOrUpdateUserLikedPhotos(photosList?.asDatabaseUserLikedPhotoModel()!!)
                }catch (exception:Exception)
                {
                    Log.d(TAG, "retriveUserLikedPhotosToTest: ${exception.message}")
                }
            }

        }
    }
}

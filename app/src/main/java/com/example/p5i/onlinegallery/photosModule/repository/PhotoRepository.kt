package com.example.p5i.onlinegallery.photosModule.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.collectionsModule.datlayer.network.CollectionAPI
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.photosModule.datalayer.network.Photos
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDatabasePhotoModel
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

private const val TAG = "PhotoRepository"
class PhotoRepository(private val unsplashDatabase: UnsplashDatabase,private val credentials:String)
{
    val photos=Transformations.map(unsplashDatabase.photosDao.getAllPhotos(),{
        it.asDomainModel()
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
}

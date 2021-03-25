package com.example.p5i.onlinegallery.photoModule.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDomainModel
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDomainModelFromCollectionPhoto
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDomainModelFromTopicPhoto

private const val TAG = "SinglePhotoRepository"
class SinglePhotoRepository (private val unsplashDatabase: UnsplashDatabase)
{
    val photos= Transformations.map(unsplashDatabase.photosDao.getAllPhotos(),
        {
            Log.d(TAG, "getAllPhotos: ${it.size} ")
        it.asDomainModel()
    })
    val photosTopic= Transformations.map(unsplashDatabase.photosDao.getAllPhotosFromTopic(),
        {
            Log.d(TAG, "getAllPhotosFromTopic: ${it.size}")
        it.asDomainModelFromTopicPhoto()
    })
    val photosCollection=
        Transformations.map(unsplashDatabase.photosDao.getAllPhotosFromCollection(),
            {
                Log.d(TAG, "getAllPhotosFromCollection: ${it.size}")
        it.asDomainModelFromCollectionPhoto()
    })
}
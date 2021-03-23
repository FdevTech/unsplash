package com.example.p5i.onlinegallery.photoModule.repository

import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDomainModel
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDomainModelFromCollectionPhoto
import com.example.p5i.onlinegallery.photosModule.datalayer.network.photosDatbase.asDomainModelFromTopicPhoto

class SinglePhotoRepository (private val unsplashDatabase: UnsplashDatabase)
{
    val photos= Transformations.map(unsplashDatabase.photosDao.getAllPhotos(),{
        it.asDomainModel()
    })
    val photosTopic= Transformations.map(unsplashDatabase.photosDao.getAllPhotosFromTopic(),{
        it.asDomainModelFromTopicPhoto()
    })
    val photosCollection=
        Transformations.map(unsplashDatabase.photosDao.getAllPhotosFromCollection(),{
        it.asDomainModelFromCollectionPhoto()
    })
}
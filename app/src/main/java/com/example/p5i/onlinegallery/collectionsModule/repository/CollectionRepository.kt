package com.example.p5i.onlinegallery.collectionsModule.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.collectionsModule.collectionDatabase.asCollectionDomain
import com.example.p5i.onlinegallery.collectionsModule.collectionDatabase.asCollectionEntity
import com.example.p5i.onlinegallery.collectionsModule.datlayer.network.CollectionAPI
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "CollectionRepository"
class CollectionRepository (private val unsplashDatabase: UnsplashDatabase, private val credentials:String)
{
    val collections= Transformations.map(unsplashDatabase.collectionDao.getAllinCollection(),{
         it.asCollectionDomain()
    })
     suspend fun refrechCollections()
     {
         withContext(Dispatchers.IO)
         {
             val collectionList=CollectionAPI.retrofitService.getCollection(credentials)
             Log.d(TAG, "refrechCollections: ${collectionList.size}")
             unsplashDatabase.collectionDao.insertOrUpdateCollection(collectionList.asCollectionEntity())
         }
     }
}
package com.example.p5i.onlinegallery.collectionsModule.repository

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.Util.POJs.CollectionPOJ
import com.example.p5i.onlinegallery.collectionsModule.collectionDatabase.asCollectionDomain
import com.example.p5i.onlinegallery.collectionsModule.collectionDatabase.asCollectionEntity
import com.example.p5i.onlinegallery.collectionsModule.collectionDatabase.asUserCollectionDomain
import com.example.p5i.onlinegallery.collectionsModule.collectionDatabase.asUserCollectionEntity
import com.example.p5i.onlinegallery.collectionsModule.datlayer.network.CollectionAPI
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "CollectionRepository"
class CollectionRepository (private val unsplashDatabase: UnsplashDatabase, private val credentials:String)
{   lateinit var Collections:Set<CollectionPOJ>
    val collections= Transformations.map(unsplashDatabase.collectionDao.getAllinCollection(),{
         it.asCollectionDomain()
    })
    val userCollections= Transformations.map(unsplashDatabase.collectionDao.getAllinUserCollection(),{
        Log.d(TAG, "retriving data from databse :${it.size} ")
        it.asUserCollectionDomain()
    })
     suspend fun refrechCollections()
     {

         var i:Int=1
         withContext(Dispatchers.IO)
         {
             var collectionList=CollectionAPI.retrofitService.getCollection(credentials).body()
             val xtotal= CollectionAPI.retrofitService.getCollection(credentials).headers().get("X-Total")
             val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
             Log.d(TAG, "refrechCollections error: ${CollectionAPI.retrofitService.getCollection(credentials).code()}")
             Log.d(TAG, "refrechCollections perpage: ${collectionList?.size}")
             Log.d(TAG, "refrechCollections total: $xtotal")
             Log.d(TAG, "refrechCollections total page: $pages")
             if(collectionList!=null)
             {
                 while(i.compareTo(pages!!)<=0)
                 {
                     Log.d(TAG, "refrechCollections: $i")
                     try {
                         collectionList=CollectionAPI.retrofitService.getCollection(credentials,page = i).body()
                         unsplashDatabase.collectionDao.insertOrUpdateCollection(collectionList?.asCollectionEntity()!!)
                     }catch (error:Exception)
                     {
                         Log.d(TAG, "refrechCollections: ${error.message}")
                     }
                     i++
                     
                 }



             }
         }
     }
    suspend fun refrechCollectionToTest()
    {

        var i:Int=1
        withContext(Dispatchers.IO)
        {
            var collectionList=CollectionAPI.retrofitService.getCollection(credentials).body()
            val xtotal= CollectionAPI.retrofitService.getCollection(credentials).headers().get("X-Total")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            Log.d(TAG, "refrechCollections error: ${CollectionAPI.retrofitService.getCollection(credentials).code()}")
            Log.d(TAG, "refrechCollections perpage: ${collectionList?.size}")
            Log.d(TAG, "refrechCollections total: $xtotal")
            Log.d(TAG, "refrechCollections total page: $pages")
            if(collectionList!=null)
            {
                try {
                    collectionList=CollectionAPI.retrofitService.getCollection(credentials).body()
                    unsplashDatabase.collectionDao.insertOrUpdateCollection(collectionList?.asCollectionEntity()!!)
                }catch (error:Exception)
                {
                    Log.d(TAG, "refrechCollections: ${error.message}")
                }



            }
        }
    }

    suspend fun refrechUserCollectionToTest(user:String)
    {

        var i:Int=1
        withContext(Dispatchers.IO)
        {
            val response=CollectionAPI.retrofitService.getUserCollection(credentials,"curta")
            var collectionList=response.body()
            val xtotal= response.headers().get("X-Total")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            Log.d(TAG, "refrechCollections response code: ${response.code()}")
            Log.d(TAG, "refrechUserCollectionToTest perpage: ${collectionList?.size}")
           // Log.d(TAG, "refrechCollections total: $xtotal")
            //Log.d(TAG, "refrechCollections total page: $pages")
            if(collectionList!=null)
            {
                Log.d(TAG, "refrechUserCollectionToTest: inside the if test")
                try {
                   // collectionList=CollectionAPI.retrofitService.getCollection(credentials).body()
                    Log.d(TAG, "refrechUserCollectionToTest: insede the try block")
                    val newlist=collectionList?.asUserCollectionEntity()
                    Log.d(TAG, "refrechUserCollectionToTest:newlist=> ${newlist.size}")
                    unsplashDatabase.collectionDao.insertOrUpdateUserCollection(collectionList?.asUserCollectionEntity()!!)
                }catch (error:Exception)
                {
                    Log.d(TAG, "refrechCollections: insde the catch ${error.message}")
                }



            }
            else
            {
                Log.d(TAG, "refrechUserCollectionToTest: outside the elf test")
            }
        }
    }
}
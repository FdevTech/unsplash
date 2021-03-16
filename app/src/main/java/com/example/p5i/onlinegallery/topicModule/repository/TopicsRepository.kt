package com.example.p5i.onlinegallery.topicModule.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.topicModule.databse.asTopicsEntity
import com.example.p5i.onlinegallery.topicModule.databse.astopicsDomain
import com.example.p5i.onlinegallery.topicModule.networkLayer.TopicsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "TopicsRepository"
class TopicsRepository (val unsplashDatabase: UnsplashDatabase,val credentials:String)
{
   val TopicsList= Transformations.map(unsplashDatabase.topicsDAO.gettopics(),{
       Log.d(TAG, "it->:${it.size} ")
       it.astopicsDomain()
   })

    suspend fun refrechTopics()
    {
        Log.d(TAG, "refrechTopics: ${unsplashDatabase.topicsDAO.gettopics().value?.size}")
        withContext(Dispatchers.IO)
        {
            val data=TopicsAPI.topics.getTopics(credentials)
            Log.d(TAG, "refrechTopics: ${data.size} ")
            unsplashDatabase.topicsDAO.insertOrUpdate(data.asTopicsEntity())

        }
    }
}
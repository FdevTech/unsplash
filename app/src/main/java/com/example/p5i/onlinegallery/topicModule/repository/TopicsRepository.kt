package com.example.p5i.onlinegallery.topicModule.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.photosModule.datalayer.network.Photos
import com.example.p5i.onlinegallery.topicModule.databse.asTopicsEntity
import com.example.p5i.onlinegallery.topicModule.databse.astopicsDomain
import com.example.p5i.onlinegallery.topicModule.networkLayer.TopicsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

private const val TAG = "TopicsRepository"
class TopicsRepository (val unsplashDatabase: UnsplashDatabase,val credentials:String)
{
   val TopicsList= Transformations.map(unsplashDatabase.topicsDAO.gettopics(),{
       Log.d(TAG, "it->:${it.size} ")
       it.astopicsDomain()
   })

    suspend fun refrechTopics()
    {
        var i:Int=1
        Log.d(TAG, "refrechTopics: ${unsplashDatabase.topicsDAO.gettopics().value?.size}")
        withContext(Dispatchers.IO)
        {
            var topicsList=TopicsAPI.topics.getTopics(credentials).body()
            val xtotal= TopicsAPI.topics.getTopics(credentials).headers().get("X-Total")
            val pages:Int?=(xtotal?.toInt()?.div(30))?.toInt()
            if(topicsList!=null)
            {
                while(i.compareTo(pages!!)<=0)
                {
                    try {
                        topicsList=TopicsAPI.topics.getTopics(credentials,page = i).body()
                        Log.d(TAG, "refrechTopics: ${topicsList?.size} ")
                        unsplashDatabase.topicsDAO.insertOrUpdate(topicsList?.asTopicsEntity()!!)
                    }catch (error:Exception)
                    {
                        Log.d(TAG, "refrechTopics: ${error.message}")
                    }
                    i++
                }
            }


        }
    }
}
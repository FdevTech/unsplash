package com.example.p5i.onlinegallery.topicModule.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.databse.getDatabse
import com.example.p5i.onlinegallery.topicModule.repository.TopicsRepository
import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "TopicsViewModel"
class TopicsViewModel(val application: Application, val credentials:String):ViewModel()
{
    val topicsRepository= TopicsRepository(getDatabse(application),credentials)
    val topics=topicsRepository.TopicsList
    init
    {
        Log.d(TAG, ":${topics.value?.size} ")
        refrechFromTopicRepository()
    }

    private fun refrechFromTopicRepository()
    {
        viewModelScope.launch {
            try {
                topicsRepository.refrechTopics()
            }catch (excpetion:IOException)
            {
                Log.d(TAG, "refrechFromTopicRepository: ${excpetion.message}")
            }
        }
    }
}
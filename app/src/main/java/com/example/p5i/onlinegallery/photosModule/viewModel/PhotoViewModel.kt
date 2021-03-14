package com.example.p5i.onlinegallery.photosModule.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.topicModule.networkLayer.TopicsAPI
import kotlinx.coroutines.launch

private const val TAG = "photoViewModel"
private lateinit var credential:String
class PhotoViewModel(val credential:String) :ViewModel()
{

    init {
        Log.d(TAG, "ViewModel init: $credential")
        initPhotoData()
    }

    fun initPhotoData()
    {

        viewModelScope.launch {
         try {
             val value= TopicsAPI.topics.getPhotosFromTopics(credential)
             Log.d(TAG, "initPhotoData: ${value.size}")
         }catch (t:Throwable)
         {
             Log.d(TAG, "initPhotoData: ${t.message}")
         }
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}
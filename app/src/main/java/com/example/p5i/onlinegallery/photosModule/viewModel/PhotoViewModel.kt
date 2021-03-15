package com.example.p5i.onlinegallery.photosModule.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.databse.getDatabse
import com.example.p5i.onlinegallery.photosModule.repository.PhotoRepository
import com.example.p5i.onlinegallery.topicModule.networkLayer.TopicsAPI
import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "photoViewModel"
private lateinit var credential:String
class PhotoViewModel(application: Application, val credential:String) :ViewModel()
{
    val photoRepository= PhotoRepository(getDatabse(application),credential)
    val photosRetrived=photoRepository.photos
    init {
        Log.d(TAG, "ViewModel init: $credential")
        Log.d(TAG, "photosRetrived size:${photosRetrived.value?.size} ")
        refrechFromRepository()
    }



    private fun refrechFromRepository()
    {
        Log.d(TAG, "refrechFromRepository: ")
        viewModelScope.launch {
            try {
                photoRepository.referchPhotos()
            }catch (network:IOException)
            {
                Log.d(TAG, "refrechFromRepository: ${network.message}")
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}
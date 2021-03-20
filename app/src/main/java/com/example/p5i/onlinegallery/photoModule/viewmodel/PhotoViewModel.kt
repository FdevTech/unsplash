package com.example.p5i.onlinegallery.photoModule.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p5i.onlinegallery.databse.getDatabse
import com.example.p5i.onlinegallery.photosModule.repository.PhotoRepository

import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "PhotoViewModel"
class PhotoViewModel(val application: Application, val credential:String):ViewModel()
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
            }catch (network: IOException)
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
package com.example.p5i.onlinegallery.homeFragment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p5i.onlinegallery.databse.getDatabse
import com.example.p5i.onlinegallery.photosModule.repository.PhotoRepository
import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "HomeFragmentViewModel"
class HomeFragmentViewModel (val application: Application, val credential:String):ViewModel()
{
    val photoRepository= PhotoRepository(getDatabse(application),credential)
    val photosRetrived=photoRepository.photos
    private var _eroorCode= MutableLiveData<Int>()
    val eroorCode=photoRepository.eroorCode
    init {
        refrechFromRepository()
    }
    private fun refrechFromRepository()
    {
        Log.d(TAG, "refrechFromRepository: ")
        viewModelScope.launch {
            try {
                //photoRepository.referchPhotos()
                photoRepository.referchPhotosToTest()
            }catch (network: IOException)
            {
                Log.d(TAG, "refrechFromRepository: ${network.message}")
            }
        }
    }
    fun like_unlikePhoto(photo_id:String)
    {
        viewModelScope.launch {
            Log.d(TAG, "like_unlikePhoto: photoid $photo_id")
            photoRepository.like_unlikePhoto(photo_id)
        }
    }
    fun likeDesLikePhoto(photoid:String)
    {
        viewModelScope.launch {
            photoRepository.likeDesLikePhoto(photoid)
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}
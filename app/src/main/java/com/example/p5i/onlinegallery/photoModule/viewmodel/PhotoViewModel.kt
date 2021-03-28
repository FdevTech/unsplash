package com.example.p5i.onlinegallery.photoModule.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p5i.onlinegallery.databse.getDatabse
import com.example.p5i.onlinegallery.photoModule.repository.SinglePhotoRepository
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain
import com.example.p5i.onlinegallery.photosModule.repository.PhotoRepository

import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "PhotoViewModel"
class PhotoViewModel(val application: Application, val credential:String,from:String?):ViewModel()
{

    private lateinit var _photosRetrived:MutableLiveData<List<PhotoDomain>>
    val photosRetrived:LiveData<List<PhotoDomain>>
    get(){
        return _photosRetrived
    }
    val singlePhotoRepository= SinglePhotoRepository(getDatabse(application))
    init {
        Log.d(TAG, "ViewModel init: $credential")

        Log.d(TAG, ":$from ")
        refrechFromRepository(from)
    }
    private fun refrechFromRepository(from:String?)
    {
        Log.d(TAG, "refrechFromRepository: ")
        if(from==null)
        {
            _photosRetrived=singlePhotoRepository.photos as MutableLiveData<List<PhotoDomain>>
            Log.d(TAG, "photosRetrived from: $from size:${photosRetrived.value?.size} ")
        }
        else if(from=="topics")
        {
            _photosRetrived=singlePhotoRepository.photosTopic as MutableLiveData<List<PhotoDomain>>
            Log.d(TAG, "photosRetrived from: $from size:${photosRetrived.value?.size} ")
        }
        else
        {
            _photosRetrived=singlePhotoRepository.photosTopic as MutableLiveData<List<PhotoDomain>>
            Log.d(TAG, "photosRetrived from: $from size:${photosRetrived.value?.size} ")
        }


    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}
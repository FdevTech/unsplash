package com.example.p5i.onlinegallery.singlePhotoModule.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.p5i.onlinegallery.databse.getDatabse
import com.example.p5i.onlinegallery.singlePhotoModule.repository.SinglePhotoRepository
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain

private const val TAG = "PhotoViewModel"
class PhotoViewModel(val application: Application, val credential:String,from:String?):ViewModel()
{
    //val photoRepository= PhotoRepository(getDatabse(application),credential)
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
            _photosRetrived=singlePhotoRepository.photosCollection as MutableLiveData<List<PhotoDomain>>
            Log.d(TAG, "photosRetrived from: $from size:${photosRetrived.value?.size} ")
        }

        /*viewModelScope.launch {
            try {
               // photoRepository.referchPhotos()
                photoRepository.referchPhotosToTest()
            }catch (network: IOException)
            {
                Log.d(TAG, "refrechFromRepository: ${network.message}")
            }
        }*/
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}
package com.example.p5i.onlinegallery.photosModule.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p5i.onlinegallery.databse.getDatabse
import com.example.p5i.onlinegallery.photosModule.repository.PhotoRepository
import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "photoViewModel"

class PhotosViewModel(application: Application, val credential:String,val topics:String?=null,val collectionId:String?=null) :ViewModel()
{
    val photoRepository= PhotoRepository(getDatabse(application),credential)
    val photosRetrived=photoRepository.photos
    val topicsPhotosRetrived=photoRepository.photosTopic
    val collectionPhotosRetrived=photoRepository.photosCollection
    init {
        Log.d(TAG, "ViewModel init: $credential")
        Log.d(TAG, "photosRetrived size:${photosRetrived.value?.size} ")
        if(topics==null && collectionId==null)
        {
            refrechFromRepository()
        }
        else
        {
            if(topics!=null)
            {
                Log.d(TAG, "$topics ")
                retrivePhotoFromTopics(topics)
            }
            else if(collectionId!=null)
            {
                retrivePhotoFromCollection(collectionId)
            }
        }

    }



    private fun refrechFromRepository()
    {
        Log.d(TAG, "refrechFromRepository: ")
        viewModelScope.launch {
            try {
                //photoRepository.referchPhotos()
                photoRepository.referchPhotosToTest()
            }catch (network:IOException)
            {
                Log.d(TAG, "refrechFromRepository: ${network.message}")
            }
        }
    }
    private fun retrivePhotoFromTopics(topicName:String)
    {
        Log.d(TAG, "retrivePhotoFromTopics: ")
        viewModelScope.launch {
            try {
                photoRepository.retrivePhotoFromTopicsToTest(topicName)
            }catch (network:IOException)
            {
                Log.d(TAG, "retrivePhotoFromTopics: ${network.message}")
            }
        }
    }
    private fun retrivePhotoFromCollection(collectionID:String)
    {
        Log.d(TAG, "retrivePhotoFromCollection: ")
        viewModelScope.launch {
            try {
                photoRepository.retrivePhotoFromCollectionToTest(collectionID)
            }catch (network:IOException)
            {
                Log.d(TAG, "retrivePhotoFromCollection: ${network.message}")
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}
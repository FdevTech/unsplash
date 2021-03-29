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

class PhotosViewModel(application: Application, val credential:String,val topics:String?=null,val collectionId:String?=null,val user:String?,val typeOfPhotos:String?) :ViewModel()
{
    val photoRepository= PhotoRepository(getDatabse(application),credential)
    val photosRetrived=photoRepository.photos
    val topicsPhotosRetrived=photoRepository.photosTopic
    val userPhotos=photoRepository.userPhotos
    val userLikedphotos=photoRepository.userLikedPhotos
    val collectionPhotosRetrived=photoRepository.photosCollection
    init {
        //Log.d(TAG, "ViewModel init: $credential")
       // Log.d(TAG, "photosRetrived size:${photosRetrived.value?.size} ")
        Log.d(TAG, "user => $user typeOfPhotos => $typeOfPhotos topic=>  $topics collectionid => $collectionId ")
        if(topics==null && collectionId==null&&user==null)
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
            else if(user!=null)
            {
                if(typeOfPhotos=="liked")
                {
                  retriveUserLikedPhotos(user)
                }else if(typeOfPhotos=="userPhoto")
                {
                    retriveUserPhotos(user)
                }
            }
        }

    }


    fun like_unlikePhoto(photo_id:String,liked_by_user:Boolean)
    {
        viewModelScope.launch {
            Log.d(TAG, "like_unlikePhoto: photoid $photo_id, like $liked_by_user")
            photoRepository.like_unlikePhoto(photo_id)
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
    private fun retriveUserPhotos(user:String)
    {
        Log.d(TAG, "retriveUserPhotos: ")
        viewModelScope.launch {
            try {
                photoRepository.retriveUserPhotosToTest(user)
            }catch (network:IOException)
            {
                Log.d(TAG, "retriveUserPhotos: ${network.message}")
            }
        }
    }
    private fun retriveUserLikedPhotos(user:String)
    {
        Log.d(TAG, "retriveUserLikedPhotos: ")
        viewModelScope.launch {
            try {
                photoRepository.retriveUserLikedPhotosToTest(user)
            }catch (network:IOException)
            {
                Log.d(TAG, "retriveUserLikedPhotos: ${network.message}")
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}
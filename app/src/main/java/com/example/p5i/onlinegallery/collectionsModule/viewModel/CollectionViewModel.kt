package com.example.p5i.onlinegallery.collectionsModule.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p5i.onlinegallery.collectionsModule.repository.CollectionRepository
import com.example.p5i.onlinegallery.databse.getDatabse
import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "CollectionViewModel"
class CollectionViewModel (application: Application, val credential:String,val user:String?):ViewModel()
{
    val collectionRespository= CollectionRepository(getDatabse(application),credential)
    val collections=collectionRespository.collections
    val userCollections=collectionRespository.userCollections
    init {
        Log.d(TAG, "size: ${collectionRespository?.collections.value?.size} ")
        if(user!=null)
        {
            refrechUserCollectionFromRepository(user)
        }else
        {
            refrechCollectionFromRepository()
        }
    }

    private fun refrechCollectionFromRepository()
    {
        viewModelScope.launch {
            try {
               // collectionRespository.refrechCollections()
                collectionRespository.refrechCollectionToTest()
            }catch (error:IOException)
            {
                Log.d(TAG, "refrechCollectionFromRepository: ${error.message}")
            }
        }
    }
    private fun refrechUserCollectionFromRepository(user:String)
    {
        Log.d(TAG, "refrechUserCollectionFromRepository: ")
        viewModelScope.launch {
            try {
                // collectionRespository.refrechCollections()
                collectionRespository.refrechUserCollectionToTest(user)
            }catch (error:IOException)
            {
                Log.d(TAG, "refrechCollectionFromRepository: ${error.message}")
            }
        }
    }
    override fun onCleared() {
        super.onCleared()

    }
}
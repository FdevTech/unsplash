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
class CollectionViewModel (application: Application, val credential:String):ViewModel()
{
    val collectionRespository= CollectionRepository(getDatabse(application),credential)
    val collections=collectionRespository.collections
    init {
        Log.d(TAG, "size: ${collectionRespository?.collections.value?.size} ")
        refrechCollectionFromRepository()
    }

    private fun refrechCollectionFromRepository()
    {
        viewModelScope.launch {
            try {
                collectionRespository.refrechCollections()
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
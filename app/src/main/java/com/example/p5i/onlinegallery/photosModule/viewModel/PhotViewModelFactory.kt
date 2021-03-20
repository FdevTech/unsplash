package com.example.p5i.onlinegallery.photosModule.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PhotViewModelFactory(val application: Application, val credential:String):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PhotosViewModel::class.java) )
        {
            return PhotosViewModel(application,credential) as T
        }
        throw IllegalArgumentException("Unknow view Model")
    }

}

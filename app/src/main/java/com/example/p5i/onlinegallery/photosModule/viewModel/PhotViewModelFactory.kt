package com.example.p5i.onlinegallery.photosModule.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import java.lang.IllegalArgumentException

class PhotViewModelFactory(val application: Application, val credential:String):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PhotoViewModel::class.java) )
        {
            return PhotoViewModel(application,credential) as T
        }
        throw IllegalArgumentException("Unknow view Model")
    }

}

package com.example.p5i.onlinegallery.photoModule.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PhotoViewModelFactory(val application: Application, val credential:String,val from:String?):ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PhotoViewModel::class.java))
        {
            return PhotoViewModel(application,credential,from) as T
        }
        throw IllegalArgumentException("unknow viewmodel instance")
    }

}
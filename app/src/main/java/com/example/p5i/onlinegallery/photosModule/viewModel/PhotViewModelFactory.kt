package com.example.p5i.onlinegallery.photosModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PhotViewModelFactory(val credential:String):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PhotoViewModel::class.java) )
        {
            return PhotoViewModel(credential) as T
        }
        throw IllegalArgumentException("Unknow view Model")
    }

}

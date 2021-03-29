package com.example.p5i.onlinegallery.collectionsModule.viewModel

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CollectionViewModelFactory( val application: Application, val credential:String,val user:String?=null) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CollectionViewModel::class.java))
        {
            return CollectionViewModel(application ,credential,user ) as T
        }
        throw IllegalArgumentException("unknown viewmodel instance")
    }
}



package com.example.p5i.onlinegallery.topicModule.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import java.lang.IllegalArgumentException

class TopicsViewModelFactory(val application: Application, val credentials:String):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TopicsViewModel::class.java))
        {
            return TopicsViewModel(application, credentials) as T
        }
        throw IllegalArgumentException("unlknwo view model")
    }
}
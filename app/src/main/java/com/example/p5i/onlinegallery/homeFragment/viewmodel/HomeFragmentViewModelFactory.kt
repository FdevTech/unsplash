package com.example.p5i.onlinegallery.homeFragment.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class HomeFragmentViewModelFactory (val application: Application, val credential:String):ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeFragmentViewModel::class.java))
        {
            return HomeFragmentViewModel(
                application,
                credential
            ) as T
        }
        throw IllegalArgumentException("not known the viewmodel")
    }

}
package com.example.p5i.onlinegallery.collectionsModule.homeFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.p5i.onlinegallery.collectionsModule.homeFragment.viewModel.HomeFragmentViewModel
import java.lang.IllegalArgumentException

class HomeFragmentViewModelFactory (val application: Application, val credential:String):ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeFragmentViewModel::class.java))
        {
            return HomeFragmentViewModel(application,credential) as T
        }
        throw IllegalArgumentException("not known the viewmodel")
    }

}
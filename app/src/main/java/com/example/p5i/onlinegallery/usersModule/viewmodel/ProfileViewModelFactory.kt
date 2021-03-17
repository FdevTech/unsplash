package com.example.p5i.onlinegallery.usersModule.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ProfileViewModelFactory (val application: Application, val credential:String):ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(ProfileViewModel::class.java))
        {
            return ProfileViewModel(application,credential) as  T
        }
        throw IllegalArgumentException("unknwo viewmodel instance")
    }

}
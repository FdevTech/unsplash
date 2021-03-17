package com.example.p5i.onlinegallery.usersModule.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p5i.onlinegallery.databse.getDatabse
import com.example.p5i.onlinegallery.usersModule.repository.ProfileRepository
import kotlinx.coroutines.launch
import java.io.IOException


private const val TAG = "ProfileViewModel"

class ProfileViewModel (val application:Application,val credential:String): ViewModel()
{
    val profileRepository= ProfileRepository(getDatabse(application),credential)
    val profiledata=profileRepository.profile
    init {
        refrechFromProfileRepository()
    }
    fun refrechFromProfileRepository()
    {
        viewModelScope.launch {
           try {
               profileRepository.refrechProfileData()
           }
           catch (excption:IOException)
           {
               Log.d(TAG, "refrechFromProfileRepository: ${excption.message}")
           }


        }
    }
}



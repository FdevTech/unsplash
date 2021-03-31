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

class ProfileViewModel (val application:Application,val credential:String,val user:String?): ViewModel()
{
    val profileRepository= ProfileRepository(getDatabse(application),credential)
    val profiledata=profileRepository.profile
    val photgrapherProfile=profileRepository.photographer
    init {
        Log.d(TAG, "${user}: ")
        if(user!=null )
        {
            refrechPhotographerProfileFromProfileRepository(user)
        }
        else
        {
            refrechUserProfileFromProfileRepository()
        }

    }
    fun refrechUserProfileFromProfileRepository()
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
    fun refrechPhotographerProfileFromProfileRepository(user:String)
    {
        viewModelScope.launch {

            try {
                profileRepository.refrechPhotographerProfileData(user)
            }

            catch (excption:IOException)
            {
                Log.d(TAG, "refrechPhotographerProfileFromProfileRepository: ${excption.message}")
            }


        }
    }
}



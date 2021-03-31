package com.example.p5i.onlinegallery.usersModule.repository

import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
import com.example.p5i.onlinegallery.usersModule.datalayer.databse.asPhotographerProfileDomain
import com.example.p5i.onlinegallery.usersModule.datalayer.databse.asPhotographerProfileEntity
import com.example.p5i.onlinegallery.usersModule.datalayer.databse.asProfileDomain
import com.example.p5i.onlinegallery.usersModule.datalayer.databse.asProfileEntity
import com.example.p5i.onlinegallery.usersModule.network_Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

private const val TAG = "ProfileRepository"

class ProfileRepository (val unsplashDatabase: UnsplashDatabase,val credential:String)
{
    val profile= Transformations.map(unsplashDatabase.profileDAO.getProfileData(),{

        it?.asProfileDomain()
    })
    val photographer=Transformations.map(unsplashDatabase.profileDAO.getPhotographerProfileData()){
        it?.asPhotographerProfileDomain()
    }

    suspend fun refrechProfileData()
    {
        withContext(Dispatchers.IO)
        {
            try {
                val data=network_Profile.ProfileAPI.profile.getMyProfile(credential)
                unsplashDatabase.profileDAO.insertOrUpdate(data.asProfileEntity())
                Log.d(TAG, "refrechProfileData: $data")
            }catch (error:Exception)
            {
                Log.d(TAG, "refrechProfileData: ${error.message}")
            }
        }
    }
    suspend fun refrechPhotographerProfileData(username:String)
    {
        withContext(Dispatchers.IO)
        {
            unsplashDatabase.profileDAO.clearphtographertable()
            try {
                val data=network_Profile.ProfileAPI.profile.getPhotographerProfile(credential,username)
                unsplashDatabase.profileDAO.insertOrUpdatePhotographerProfile(data.asPhotographerProfileEntity())
                Log.d(TAG, "refrechPhotographerProfileData: $data")
            }catch (error:Exception)
            {
                Log.d(TAG, "refrechPhotographerProfileData: ${error.message}")
            }
        }
    }
}
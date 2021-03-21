package com.example.p5i.onlinegallery.usersModule.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.p5i.onlinegallery.databse.UnsplashDatabase
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

    suspend fun refrechProfileData()
    {
        withContext(Dispatchers.IO)
        {
            try {
                val data=network_Profile.ProfileAPI.profile.getMyProfile(credential)
                unsplashDatabase.profileDAO.insertOrUpdate(data.asProfileEntity())
            }catch (error:Exception)
            {
                Log.d(TAG, "refrechProfileData: ${error.message}")
            }
        }
    }
}
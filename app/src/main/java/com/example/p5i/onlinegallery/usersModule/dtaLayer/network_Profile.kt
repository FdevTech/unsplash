package com.example.p5i.onlinegallery.usersModule.dtaLayer

import com.example.p5i.onlinegallery.Util.POJs.UserPOJ
import com.example.p5i.onlinegallery.Util.retrofit
import retrofit2.http.GET
import retrofit2.http.Header


interface network_Profile
{
    @GET("/me")
    suspend fun getMyProfile(@Header("Authorization")autorization:String): UserPOJ

    object ProfileAPI{
        val profile:network_Profile by lazy {
            retrofit.create(network_Profile::class.java)
        }
    }
}
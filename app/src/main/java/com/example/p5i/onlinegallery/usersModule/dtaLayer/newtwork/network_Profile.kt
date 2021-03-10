package com.example.p5i.onlinegallery.usersModule.dtaLayer.newtwork

import com.example.p5i.onlinegallery.Util.POJs.UserPOJ
import com.example.p5i.onlinegallery.Util.retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT


interface network_Profile
{
    @GET("/me")
    suspend fun getMyProfile(@Header("Authorization")autorization:String): UserPOJ

    @PUT("me?first_name=Curta&last_name=Dz&location=this my location is 127.0.0.1&bio=I'm android developper")
    suspend fun updateMyInfo(@Header("Authorization")autorization:String):UserPOJ

    object ProfileAPI{
        val profile: network_Profile by lazy {
            retrofit.create(network_Profile::class.java)
        }
    }
}
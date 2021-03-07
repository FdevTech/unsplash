package com.example.p5i.onlinegallery.collectionsModule.collectionListPackage

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

private const val BASE_URL = "https://api.unsplash.com/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface Collections{

    @GET("collections?page=10&per_page=20")
    fun getCollection(@Header("Authorization") authorization:String): Call<List<CollectionPOJ>>
}

object CollectionAPI{
    val retrofitService:Collections by lazy {
        retrofit.create(Collections::class.java)
    }
}
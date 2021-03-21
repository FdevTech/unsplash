package com.example.p5i.onlinegallery.topicModule.networkLayer

import com.example.p5i.onlinegallery.Util.POJs.PhotoPOJ
import com.example.p5i.onlinegallery.Util.POJs.TopicsPOJ
import com.example.p5i.onlinegallery.Util.retrofit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface network_Topics{

    @GET("topics")
    suspend fun getTopics (@Header("Authorization")autorization:String, @Query("page")page:Int=1, @Query("per_page")per_page:Int=30):Response<List<TopicsPOJ>>

    @GET("topics/wallpapers/photos")
    suspend fun getPhotosFromTopics(@Header("Authorization")autorization:String):List<PhotoPOJ>
}
object TopicsAPI
{
    val topics:network_Topics by lazy {
        retrofit.create(network_Topics::class.java)
    }

}
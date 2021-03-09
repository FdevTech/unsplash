package com.example.p5i.onlinegallery.topicModule.networkLayer

import com.example.p5i.onlinegallery.Util.POJs.PhotoPOJ
import com.example.p5i.onlinegallery.Util.POJs.TopicsPOJ
import com.example.p5i.onlinegallery.Util.retrofit
import retrofit2.http.GET
import retrofit2.http.Header

interface network_Topics{

    @GET("topics")
    suspend fun getTopics (@Header("Authorization")autorization:String):List<TopicsPOJ>

    @GET("topics/wallpapers/photos")
    suspend fun getPhotosFromTopics(@Header("Authorization")autorization:String):List<PhotoPOJ>
}
object TopicsAPI
{
    val topics:network_Topics by lazy {
        retrofit.create(network_Topics::class.java)
    }

}
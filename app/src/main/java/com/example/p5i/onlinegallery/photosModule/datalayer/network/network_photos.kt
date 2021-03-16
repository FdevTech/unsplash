package com.example.p5i.onlinegallery.photosModule.datalayer.network

import com.example.p5i.onlinegallery.Util.POJs.LikeUnlikePOJ
import com.example.p5i.onlinegallery.Util.POJs.PhotoPOJ
import com.example.p5i.onlinegallery.Util.retrofit
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface Photos {

    @GET("photos")
    suspend fun getPhotos(@Header("Authorization")autorization:String):List<PhotoPOJ>
    @GET("photos/?per_page=100")
    suspend fun getPhoto(@Header("Authorization")autorization:String):PhotoPOJ

    @POST("photos/Z1TG5G3TArs/like")
    suspend fun likePhoto(@Header("Authorization")autorization:String): LikeUnlikePOJ

    @DELETE("photos/Z1TG5G3TArs/like")
    suspend fun unLikePhoto(@Header("Authorization")autorization:String): LikeUnlikePOJ

     object PhotosAPI {
        val photos: Photos by lazy {
            retrofit.create(Photos::class.java)
        }
    }
}
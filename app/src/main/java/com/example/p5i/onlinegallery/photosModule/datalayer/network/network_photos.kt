package com.example.p5i.onlinegallery.photosModule.datalayer.network

import com.example.p5i.onlinegallery.Util.POJs.LikeUnlikePOJ
import com.example.p5i.onlinegallery.Util.POJs.PhotoPOJ
import com.example.p5i.onlinegallery.Util.retrofit
import retrofit2.Response
import retrofit2.http.*


interface Photos {

    @GET("photos")
    suspend fun getPhotos(@Header("Authorization")autorization:String,@Query("page")page:Int=1, @Query("per_page")per_page:Int=30):Response<List<PhotoPOJ>>

    @GET("photos/")
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
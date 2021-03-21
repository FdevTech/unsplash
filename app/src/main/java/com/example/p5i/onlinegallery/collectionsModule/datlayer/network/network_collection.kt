package com.example.p5i.onlinegallery.collectionsModule.datlayer.network

import com.example.p5i.onlinegallery.Util.POJs.CollectionPOJ
import com.example.p5i.onlinegallery.Util.POJs.LikeUnlikePOJ
import com.example.p5i.onlinegallery.Util.POJs.PhotoPOJ
import com.example.p5i.onlinegallery.Util.retrofit
import retrofit2.Response
import retrofit2.http.*


interface Collections{

    @GET("collections?page=1&per_page=30")
    suspend fun getCollection(@Header("Authorization") authorization:String,@Query("page")page:Int=1,@Query("per_page")per_page:Int=30): Response<List<CollectionPOJ>>

    @GET("/collections/51171413/photos")
    suspend fun getCollectionPhotos(@Header("Authorization")authorization:String):List<PhotoPOJ>

    @POST("collections/?title=jack&description=it is just a test for a fucking collection&private=true")
    suspend fun create(@Header("Authorization")authorization:String):CollectionPOJ

    @PUT("collections/36223938/?title=fuck-engineers&description=it is just a test for a fucking collection")
    suspend fun update(@Header("Authorization")authorization:String):CollectionPOJ

    @DELETE("collections/74616526")
    suspend fun delete(@Header("Authorization")authorization:String):Response<Unit>

    @POST("collections/51171413/add?photo_id=QBfO2AaQvKE")
    suspend fun addPhototoaAcoolection(@Header("Authorization")authorization:String): LikeUnlikePOJ

    @DELETE("collections/51171413/remove?photo_id=g2E2NQ5SWSU")
    suspend fun deletePhotoFromaAcoolection(@Header("Authorization")authorization:String): Response<Unit>
}

object CollectionAPI{
    val retrofitService: Collections by lazy {
        retrofit.create(Collections::class.java)
    }
}
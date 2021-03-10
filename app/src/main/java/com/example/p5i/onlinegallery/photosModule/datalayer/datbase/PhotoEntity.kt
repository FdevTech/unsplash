package com.example.p5i.onlinegallery.photosModule.datalayer.datbase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photosTable")
data class PhotoEntity(
      @PrimaryKey
      var id:String?,
      var created_at:String?,
      var updated_at:String?,
      var width:Int?,
      var height:Int?,
      var color:String?,
      var blur_hash:String?,
      var description:String?,
      var alt_description:String?,
      var regular:String?,
      var small:String?,
      var thumb:String?,
      var likes:Long?,
      var liked_by_user:Boolean?



)
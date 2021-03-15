package com.example.p5i.onlinegallery.photosModule.domain

data class PhotoDomain(
    var blur_hash:String?,
    var photo_regular:String?,
    var photo_small:String?,
    var photo_thumb:String?,
    var views:Int?,
    var download: Int?
)
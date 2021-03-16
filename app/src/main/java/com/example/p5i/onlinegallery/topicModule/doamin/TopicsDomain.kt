package com.example.p5i.onlinegallery.topicModule.doamin

data class TopicsDomain(val id: String?,
                        val title:String?,
                        val description:String?,
                        val published_at:String?,
                        val updated_at:String?,
                        val total_photos:Int?,
                        val cover_photo_blur_hash:String?,
                        val cover_photo_regular:String?,
                        val cover_photo_small:String?,
                        val cover_photo_thumb:String?
    )
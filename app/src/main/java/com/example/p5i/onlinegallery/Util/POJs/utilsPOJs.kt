package com.example.p5i.onlinegallery.Util.POJs

data class URLS(val raw:String,
                 val full:String,
                val regular:String,
                val small:String,
                val thumb:String
                )

data class Links(val self:String,
                 val html:String,
                 val download:String,
                 val download_location:String,
                 val photos:String,
                 val likes:String,
                 val portfolio:String,
                 val following:String,
                 val followers:String
                 )



data class profileImage(val small:String,
                        val medium:String,
                        val large:String
)

data class PhotoPOJ(val id:String,
                    val created_at:String,
                    val updated_at:String,
                    val promoted_at:Any,
                    val width:Int,
                    val height:Int,
                    val color:String,
                    val blur_hash:String,
                    val description:String,
                    val alt_description:String,
                    val urls: URLS,
                    val links: Links,
                    val likes:Int,
                    val liked_by_user:Boolean,
                    val user: UserPOJ
)
data class UserPOJ (val id:String,
                    val updated_at:String,
                    val username:String,
                    val name:String,
                    val first_name:String,
                    val last_name:String,
                    val twitter_username:String,
                    val portfolio_url:String,
                    val bio:String,
                    val location:String,
                    val profile_image: profileImage,
                    val instagram_username:String,
                    val total_collections:Int,
                    val total_likes:Int,
                    val total_photos:Int,
                    val accepted_tos:Boolean,
                    val for_hire:Boolean
                 )

data class CollectionPOJ(val id:String,
                         val title:String,
                         val published_at:String,
                         val last_collected_at:String,
                         val updated_at:String,
                         val featured:Boolean,
                         val total_photos :Int,
                         val share_key:String,
                         val user: UserPOJ,
                         val cover_photo: PhotoPOJ,
                         val preview_photos: List<PhotoPOJ>
)

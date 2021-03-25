package com.example.p5i.onlinegallery.photosModule.viewModel

import android.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain


private const val TAG = "photoDataAdapter"


@BindingAdapter("profile_avatar")
fun ImageView.setAvatr(data: PhotoDomain)
{
    Glide.with(context)
        .load(data.user_profile_image_small)
        .into(this)
}

@BindingAdapter("profile_name")
fun TextView.setName(data: PhotoDomain)
{
   text="${data.user_first_name} ${data.user_last_name}"
}

@BindingAdapter("image")
fun ImageView.get(data: PhotoDomain)
{
   /* val blurHash: BlurHash = BlurHash(this.context, lruSize = 20, punch = 1F)
    data.blur_hash?.let {
        Glide.with(context)
        .load(data.photo_small)
        .blurPlaceHolder(it,this, blurHash)
        { requestBuilder ->
            requestBuilder.into(this)
        }
    }*/
    val requestOptions =
        RequestOptions()
    requestOptions.placeholder(R.drawable.picture_frame)


    val thumbnail=Glide.with(this.context).load(data.photo_thumb)
    Glide.with(this.context)
        .load(data.photo_small)
        .thumbnail(thumbnail)
        .into(this)


}
@BindingAdapter("viewNumber")
fun TextView.getViewNumber(data: PhotoDomain)
{
    this.text=data.views.toString()
}

@BindingAdapter("donwloadNumber")
fun TextView.getDownloadNumber(data: PhotoDomain)
{
    this.text=data.download.toString()
}
@BindingAdapter("likedNumber")
fun TextView.getLikesNumber(data: PhotoDomain)
{
    this.text=data.likes.toString()
}
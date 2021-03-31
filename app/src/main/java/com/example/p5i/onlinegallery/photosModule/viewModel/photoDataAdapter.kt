package com.example.p5i.onlinegallery.photosModule.viewModel

import android.util.Log
import com.example.p5i.onlinegallery.R
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain


private const val TAG = "photoDataAdapter"

@BindingAdapter("anim")
fun ImageView.addAnim(data: PhotoDomain)
{
    Log.d(TAG, "addAnim: ${data.liked_by_user}")

    if(data.liked_by_user!!)
    {
        setImageResource(R.drawable.ic_like_icon)
    }
    else
    {
        setImageResource(R.drawable.ic_unlike_icon)
    }
    invalidate()
}

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
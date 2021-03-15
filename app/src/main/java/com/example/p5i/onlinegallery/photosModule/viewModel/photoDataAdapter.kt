package com.example.p5i.onlinegallery.photosModule.viewModel

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain


@BindingAdapter("image")
fun ImageView.get(data: PhotoDomain)
{
    Glide.with(context)
        .load(data.photo_regular)
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
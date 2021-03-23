package com.example.p5i.onlinegallery.photoModule.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain

@BindingAdapter("image")
fun ImageView.setImage(photo: PhotoDomain)
{
    Glide.with(this.context)
        .load(photo.photo_regular)
        .into(this)
}
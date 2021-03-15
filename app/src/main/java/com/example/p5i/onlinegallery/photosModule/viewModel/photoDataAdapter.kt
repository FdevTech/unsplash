package com.example.p5i.onlinegallery.photosModule.viewModel

import android.widget.ImageView
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
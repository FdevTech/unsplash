package com.example.p5i.onlinegallery.photoModule.viewmodel

import android.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.p5i.onlinegallery.collectionsModule.viewModel.getSecpond
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain
import com.example.p5i.onlinegallery.photosModule.viewModel.get
import xyz.belvi.blurhash.BlurHash
import xyz.belvi.blurhash.blurPlaceHolder

@BindingAdapter("image")
fun ImageView.setImage(photo: PhotoDomain)
{



    val thumbnail=Glide.with(this.context).load(photo.photo_thumb)
    Glide.with(this.context)
        .load(photo.photo_regular)
        .thumbnail(thumbnail)
        .into(this)

}
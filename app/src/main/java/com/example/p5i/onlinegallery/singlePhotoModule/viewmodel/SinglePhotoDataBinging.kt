package com.example.p5i.onlinegallery.singlePhotoModule.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain

@BindingAdapter("image")
fun ImageView.setImage(photo: PhotoDomain)
{
    //todo fix the problem of memory
    /*val blurHash: BlurHash = BlurHash(this.context, lruSize = 20, punch = 1F)
    photo.blur_hash?.let {
        Glide.with(context)
            .load(photo.photo_regular)
            .blurPlaceHolder(it,this, blurHash)
            { requestBuilder ->
                requestBuilder.into(this)
            }
    }*/
    Glide.with(this.context)
        .load(photo.photo_regular)
        .into(this)
}
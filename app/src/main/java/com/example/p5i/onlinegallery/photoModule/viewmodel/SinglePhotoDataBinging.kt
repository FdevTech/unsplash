package com.example.p5i.onlinegallery.photoModule.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.collectionsModule.viewModel.getSecpond
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain
import xyz.belvi.blurhash.BlurHash
import xyz.belvi.blurhash.blurPlaceHolder

@BindingAdapter("image")
fun ImageView.setImage(photo: PhotoDomain)
{
    //todo fix the problem of memory
    val blurHash: BlurHash = BlurHash(this.context, lruSize = 20, punch = 1F)
    photo.blur_hash?.let {
        Glide.with(context)
            .load(photo.photo_regular)
            .blurPlaceHolder(it,this, blurHash)
            { requestBuilder ->
                requestBuilder.into(this)
            }
    }
   /* Glide.with(this.context)
        .load(photo.photo_regular)
        .into(this)*/
}
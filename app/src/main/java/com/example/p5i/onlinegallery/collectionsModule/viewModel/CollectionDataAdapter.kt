package com.example.p5i.onlinegallery.collectionsModule.viewModel

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.collectionsModule.datlayer.domainedata.CollectionDomain
import com.example.p5i.onlinegallery.photosModule.viewModel.get
import xyz.belvi.blurhash.BlurHash
import xyz.belvi.blurhash.blurPlaceHolder

private const val TAG = "CollectionDataAdapter"
@BindingAdapter("cover")
fun ImageView.getCover(data:CollectionDomain)
{
    Log.d(TAG, "getCover: ${data.cover_photo_small}")
   /* Glide.with(context)
        .load(data.cover_photo_small)
        .into(this)*/
    val blurHash: BlurHash = BlurHash(this.context, lruSize = 20, punch = 1F)
    data.cover_photo_blur_hash?.let {
        Glide.with(context)
            .load(data.cover_photo_regular)
            .blurPlaceHolder(it,this, blurHash)
            { requestBuilder ->
                requestBuilder.into(this)
            }
    }
}

@BindingAdapter("first")
fun ImageView.getFirst(data:CollectionDomain)
{
    Log.d(TAG, "getFirst: ${data.third_photo_small}")
    /*Glide.with(context)
        .load(data.third_photo_small)
        .into(this)*/
    val blurHash: BlurHash = BlurHash(this.context, lruSize = 20, punch = 1F)
    data.third_photo_blur_hash?.let {
        Glide.with(context)
            .load(data.third_photo_regular)
            .blurPlaceHolder(it,this, blurHash)
            { requestBuilder ->
                requestBuilder.into(this)
            }
    }
}
@BindingAdapter("second")
fun ImageView.getSecpond(data:CollectionDomain)
{
    Log.d(TAG, "getSecpond: ${data.second_photo_small}")
   /* Glide.with(context)
        .load(data.second_photo_small)
        .into(this)*/
    val blurHash: BlurHash = BlurHash(this.context, lruSize = 20, punch = 1F)
    data.second_photo_blur_hash?.let {
        Glide.with(context)
            .load(data.second_photo_regular)
            .blurPlaceHolder(it,this, blurHash)
            { requestBuilder ->
                requestBuilder.into(this)
            }
    }
}

@BindingAdapter("collectionTitle")
fun TextView.getCollectionTitle(data:CollectionDomain)
{
    text=data.title
}
@BindingAdapter("collectionTotalPhotos")
fun TextView.getTotalPhoto(data:CollectionDomain)
{
    text=data.total_photos.toString()
}
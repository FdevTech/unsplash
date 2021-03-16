package com.example.p5i.onlinegallery.collectionsModule.viewModel

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.collectionsModule.datlayer.domainedata.CollectionDomain

private const val TAG = "CollectionDataAdapter"
@BindingAdapter("cover")
fun ImageView.getCover(data:CollectionDomain)
{
    Log.d(TAG, "getCover: ${data.cover_photo_regular}")
    Glide.with(context)
        .load(data.cover_photo_small)
        .into(this)
}

@BindingAdapter("first")
fun ImageView.getFirst(data:CollectionDomain)
{
    Log.d(TAG, "getFirst: ${data.third_photo_regular}")
    Glide.with(context)
        .load(data.third_photo_small)
        .into(this)
}
@BindingAdapter("second")
fun ImageView.getSecpond(data:CollectionDomain)
{
    Log.d(TAG, "getSecpond: ${data.second_photo_regular}")
    Glide.with(context)
        .load(data.second_photo_small)
        .into(this)
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
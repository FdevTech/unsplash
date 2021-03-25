package com.example.p5i.onlinegallery.collectionsModule.viewModel

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.collectionsModule.datlayer.domainedata.CollectionDomain
import com.example.p5i.onlinegallery.photoModule.viewmodel.setImage
import com.example.p5i.onlinegallery.photosModule.viewModel.get
import xyz.belvi.blurhash.BlurHash
import xyz.belvi.blurhash.blurPlaceHolder

private const val TAG = "CollectionDataAdapter"
@BindingAdapter("cover")
fun ImageView.getCover(data:CollectionDomain)
{
    val thumbnail=Glide.with(this.context).load(data.cover_photo_thumb)
    Glide.with(this.context)
        .load(data.cover_photo_small)
        .thumbnail(thumbnail)
        .into(this)
}

@BindingAdapter("first")
fun ImageView.getFirst(data:CollectionDomain)
{
    val thumbnail=Glide.with(this.context).load(data.second_photo_thumb)
    Glide.with(this.context)
        .load(data.second_photo_small)
        .thumbnail(thumbnail)
        .into(this)
}
@BindingAdapter("second")
fun ImageView.getSecpond(data:CollectionDomain)
{
    val thumbnail=Glide.with(this.context).load(data.third_photo_thumb)
    Glide.with(this.context)
        .load(data.third_photo_small)
        .thumbnail(thumbnail)
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
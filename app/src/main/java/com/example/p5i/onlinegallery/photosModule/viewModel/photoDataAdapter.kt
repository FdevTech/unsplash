package com.example.p5i.onlinegallery.photosModule.viewModel

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain
import com.mikhaellopez.circularimageview.CircularImageView
import xyz.belvi.blurhash.BlurHash
import xyz.belvi.blurhash.blurPlaceHolder

private const val TAG = "photoDataAdapter"


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
    val blurHash: BlurHash = BlurHash(this.context, lruSize = 20, punch = 1F)
    data.blur_hash?.let {
        Glide.with(context)
        .load(data.photo_regular)
        .blurPlaceHolder(it,this, blurHash)
        { requestBuilder ->
            requestBuilder.into(this)
        }
    }
    /*Glide.with(context)
        .load(data.photo_regular)
        .into(this)*/

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
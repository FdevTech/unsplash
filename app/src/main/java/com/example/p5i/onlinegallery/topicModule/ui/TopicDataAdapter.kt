package com.example.p5i.onlinegallery.topicModule.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.collectionsModule.viewModel.getCover
import com.example.p5i.onlinegallery.topicModule.doamin.TopicsDomain
import xyz.belvi.blurhash.BlurHash
import xyz.belvi.blurhash.blurPlaceHolder

@BindingAdapter("coverTopic")
fun ImageView.setTopicCover(data:TopicsDomain)
{
  /*  Glide.with(context)
        .load(data.cover_photo_regular)
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

@BindingAdapter("titleTopics")
fun TextView.getTopicTitle(data:TopicsDomain)
{
    text=data.title
}

@BindingAdapter("descriptionTopics")
fun TextView.getTopicDesciption(data:TopicsDomain)
{
    text=data.description
}
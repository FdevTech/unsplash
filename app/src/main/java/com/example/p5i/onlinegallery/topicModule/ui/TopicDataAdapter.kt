package com.example.p5i.onlinegallery.topicModule.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.collectionsModule.viewModel.getCover
import com.example.p5i.onlinegallery.photoModule.viewmodel.setImage
import com.example.p5i.onlinegallery.topicModule.doamin.TopicsDomain
import xyz.belvi.blurhash.BlurHash
import xyz.belvi.blurhash.blurPlaceHolder

@BindingAdapter("coverTopic")
fun ImageView.setTopicCover(data:TopicsDomain)
{
    val thumbnail=Glide.with(this.context).load(data.cover_photo_thumb)
    Glide.with(this.context)
        .load(data.cover_photo_regular)
        .thumbnail(thumbnail)
        .into(this)
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
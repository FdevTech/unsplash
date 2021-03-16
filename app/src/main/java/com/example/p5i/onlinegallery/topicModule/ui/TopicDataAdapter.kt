package com.example.p5i.onlinegallery.topicModule.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.topicModule.doamin.TopicsDomain

@BindingAdapter("coverTopic")
fun ImageView.setTopicCover(data:TopicsDomain)
{
    Glide.with(context)
        .load(data.cover_photo_regular)
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
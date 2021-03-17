package com.example.p5i.onlinegallery.usersModule.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.usersModule.domain.ProfileDomain

@BindingAdapter("profileImage")
fun ImageView.getProfileImage(data:ProfileDomain?)
{
   data?.let {
       Glide.with(context)
           .load(it.profile_image_medium)
           .into(this)
   }
}

@BindingAdapter("profileName")
fun TextView.getProfileName(data:ProfileDomain?)
{
    data?.let {
        text=it.username
    }
}
@BindingAdapter("profileLocation")
fun TextView.getProfileLocation(data:ProfileDomain?)
{
    data?.let {
        text=it.location
    }
}
@BindingAdapter("profileBio")
fun TextView.getProfileAbout(data:ProfileDomain?)
{
    data?.let {
        text=it.bio
    }
}
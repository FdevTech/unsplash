package com.example.p5i.onlinegallery.photoModule.ui

import android.animation.ObjectAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain
import com.example.p5i.onlinegallery.photosModule.ui.PhotoViewModelAdapter
import kotlin.math.log

private const val TAG = "PhotoViewAdapter"
class PhotoViewAdapter ():ListAdapter<PhotoDomain,PhotoViewAdapter.PhotovViewHolder>(PhotoDiffUtil())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotovViewHolder {

        return PhotovViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PhotovViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")
        val data=getItem(position)
        holder.bind(data)
    }
    class PhotovViewHolder(val view: View): RecyclerView.ViewHolder(view)
    {
        var latspoition=-1
       companion object{

           fun from (parent: ViewGroup):PhotovViewHolder
           {
               val inflater=LayoutInflater.from(parent.context)
               val view=inflater.inflate(R.layout.single_photos,parent,false)
               return PhotovViewHolder(view)
           }
       }
        val image=view.findViewById<ImageView>(R.id.bigPhoto)
        fun bind(data:PhotoDomain)
        {
            Glide.with(view.context)
                .load(data.photo_regular)
                .into(image)


        }
    }
    class PhotoDiffUtil():DiffUtil.ItemCallback<PhotoDomain>()
    {
        override fun areItemsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
            return oldItem==newItem
        }

    }


}



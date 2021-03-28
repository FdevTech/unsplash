package com.example.p5i.onlinegallery.photoModule.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.databinding.SinglePhotoBinding
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain

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
    class PhotovViewHolder(val binding: SinglePhotoBinding): RecyclerView.ViewHolder(binding.root)
    {
        var latspoition=-1
       companion object{

           fun from (parent: ViewGroup):PhotovViewHolder
           {
               val inflater=LayoutInflater.from(parent.context)
               val binding=SinglePhotoBinding.inflate(inflater,parent,false)

               return PhotovViewHolder(binding)
           }
       }

        fun bind(data:PhotoDomain)
        {
            binding.photo=data
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



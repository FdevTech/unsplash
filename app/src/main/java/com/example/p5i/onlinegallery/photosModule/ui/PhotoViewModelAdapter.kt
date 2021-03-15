package com.example.p5i.onlinegallery.photosModule.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.databinding.PhotosItemBinding
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain

class PhotoViewModelAdapter() : ListAdapter<PhotoDomain,PhotoViewModelAdapter.ViewHolder>(PhotoDiffUtill()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = getItem(position)
        holder.bind(value)
    }

    class ViewHolder private constructor(val binding: PhotosItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object{
            fun from(parent: ViewGroup):ViewHolder
            {
                val inflater=LayoutInflater.from(parent.context)
                val binding=PhotosItemBinding.inflate(inflater,parent,false)
                return ViewHolder(binding)
            }
        }

        fun bind(data: PhotoDomain) {

            binding.photo=data

        }
    }


    class PhotoDiffUtill: DiffUtil.ItemCallback<PhotoDomain>()
    {
        override fun areItemsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {

            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {

           return oldItem==oldItem
        }

    }


}
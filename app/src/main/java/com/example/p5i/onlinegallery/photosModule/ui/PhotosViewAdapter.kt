package com.example.p5i.onlinegallery.photosModule.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.p5i.onlinegallery.databinding.PhotosItemBinding
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain

private const val TAG = "PhotosViewAdapter"
class PhotoViewModelAdapter(val onPhotoClickListne:OnPhotoClickListner) : ListAdapter<PhotoDomain,PhotoViewModelAdapter.ViewHolder>(PhotosDiffUtill()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = getItem(position)
        Log.d(TAG, "onBindViewHolder: $position data-> ${value.photo_regular}")
        holder.bind(value,onPhotoClickListne)
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

        fun bind(
            data: PhotoDomain,
            ohotoClickListne: OnPhotoClickListner?
        ) {

            binding.photo=data
            binding.onlickeListner=ohotoClickListne
            binding.position=adapterPosition


        }
    }


    class PhotosDiffUtill: DiffUtil.ItemCallback<PhotoDomain>()
    {
        override fun areItemsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean
        {

            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {

           return oldItem==oldItem
        }

    }




}
class OnPhotoClickListner(val onlick:(data: PhotoDomain,position:Int,from:String?)->Unit )
{
    fun onClick(data: PhotoDomain,position:Int,from:String?)
    {
        return onlick(data,position,from)
    }
}



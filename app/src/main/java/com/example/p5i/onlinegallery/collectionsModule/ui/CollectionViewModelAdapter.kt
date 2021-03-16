package com.example.p5i.onlinegallery.collectionsModule.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.p5i.onlinegallery.collectionsModule.datlayer.domainedata.CollectionDomain
import com.example.p5i.onlinegallery.databinding.CollectionListRowBinding
import com.example.p5i.onlinegallery.photosModule.ui.PhotoViewModelAdapter

class CollectionViewModelAdapter ():ListAdapter<CollectionDomain, CollectionViewModelAdapter.CollectionViewHolder>(CollectionDiffUtil())
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
       return CollectionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val data=getItem(position)
        holder.bind(data)
    }


    class CollectionViewHolder(val binding:CollectionListRowBinding): RecyclerView.ViewHolder(binding.root)
    {
        companion object{

            fun from(parent: ViewGroup):CollectionViewHolder{
                val inflater=LayoutInflater.from(parent.context)
                val binding=CollectionListRowBinding.inflate(inflater,parent,false)
                return CollectionViewHolder(binding)
            }
        }

        fun bind(collectionDomain: CollectionDomain)
        {
            binding.collectiondomain=collectionDomain
        }
    }


}






class CollectionDiffUtil():DiffUtil.ItemCallback<CollectionDomain>()
{
    override fun areItemsTheSame(oldItem: CollectionDomain, newItem: CollectionDomain): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: CollectionDomain, newItem: CollectionDomain): Boolean {
        return oldItem==newItem
    }

}
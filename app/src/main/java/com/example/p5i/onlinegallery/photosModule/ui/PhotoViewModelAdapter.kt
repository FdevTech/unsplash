package com.example.p5i.onlinegallery.photosModule.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain

class PhotoViewModelAdapter (): RecyclerView.Adapter<PhotoViewModelAdapter.ViewHolder>()
{
    var data= listOf<PhotoDomain>()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.photos_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(data.get(position).photo_regular)
            .into(holder.imageView)
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
           val imageView=itemView.findViewById<ImageView>(R.id.image)
    }
}
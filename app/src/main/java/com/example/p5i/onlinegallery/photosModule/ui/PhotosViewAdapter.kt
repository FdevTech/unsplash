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
class PhotoViewModelAdapter(val onPhotoClickListne:OnPhotoClickListner) :
    RecyclerView.Adapter<PhotoViewModelAdapter.ViewHolder>() {
    var data= listOf<PhotoDomain>()
    set(value) {
        field=value
        notifyDataSetChanged()
    }
    var profileOnClick:ProfileOnClick?=null
    var likeClic:LikeClic?=null
    var downloadClic:DownloadClic?=null


   /* fun submit(dataSubmitted:List<PhotoDomain>)
    {
        val oldlist=data
        val diffResult:DiffUtil.DiffResult=DiffUtil.calculateDiff(PhotosDiffUtill(oldlist,dataSubmitted))
        data=dataSubmitted
        diffResult.dispatchUpdatesTo(this)
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = data.get(position)
        Log.d(TAG, "onBindViewHolder: $position data-> ${value.photo_regular}")
        holder.bind(value,onPhotoClickListne,profileOnClick,likeClic,downloadClic)
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
            ohotoClickListne: OnPhotoClickListner?,
            profileOnClick: ProfileOnClick?,
            likeClic: LikeClic?,
            downloadClic: DownloadClic?
        ) {
            Log.d(TAG, "bind: ${data.id} is liked by user: ${data.liked_by_user}")
            binding.photo=data
            binding.onlickeListner=ohotoClickListne
            binding.position=adapterPosition
            binding.onProfileClicked=profileOnClick
            binding.onLikeClcik=likeClic
            binding.onDownloadClic=downloadClic


        }
    }


    class PhotosDiffUtill(var oldItem: List<PhotoDomain> , var newItem: List<PhotoDomain>): DiffUtil.Callback()
    {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            return oldItem.get(oldItemPosition).id==newItem.get(newItemPosition).id
        }

        override fun getOldListSize(): Int {
           return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem==newItem
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
class ProfileOnClick(val onPorilfeClick:(data: PhotoDomain)->Unit)
{
    fun onPorfileClciked(data: PhotoDomain)
    {
       return onPorilfeClick(data)
    }
}
class LikeClic(val onLikeCkick:(data: PhotoDomain,position:Int)->Unit)
{
    fun onLike(data: PhotoDomain,position:Int)
    {
      return onLikeCkick(data,position)
    }
}
class DownloadClic(val onDownloadCkick:(data: PhotoDomain)->Unit)
{
    fun onDownload(data: PhotoDomain)
    {
        return onDownloadCkick(data)
    }
}



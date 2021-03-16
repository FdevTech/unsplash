package com.example.p5i.onlinegallery.topicModule.viewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.p5i.onlinegallery.databinding.TopicsRowBinding
import com.example.p5i.onlinegallery.topicModule.doamin.TopicsDomain



class TopicsRecyclerAdpater():ListAdapter<TopicsDomain,TopicsRecyclerAdpater.TopicViewHolder>(TopicsDiffUtil())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
         return TopicViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    class TopicViewHolder(val binding: TopicsRowBinding) : RecyclerView.ViewHolder(binding.root)
    {
      companion object {

          fun from(parent: ViewGroup):TopicViewHolder
          {   val inflater= LayoutInflater.from(parent.context)
              val binding=TopicsRowBinding.inflate(inflater,parent,false)
              return TopicViewHolder(binding)
          }

      }
        fun bind(data:TopicsDomain)
        {
            binding.topicDomain=data
        }
    }

    class TopicsDiffUtil() : DiffUtil.ItemCallback<TopicsDomain>() {
        override fun areItemsTheSame(oldItem: TopicsDomain, newItem: TopicsDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TopicsDomain, newItem: TopicsDomain): Boolean {
            return oldItem == newItem
        }

    }


}



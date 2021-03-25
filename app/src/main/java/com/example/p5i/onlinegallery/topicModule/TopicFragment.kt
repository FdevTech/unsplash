package com.example.p5i.onlinegallery.topicModule

import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.collectionsModule.ui.CollectionViewModelAdapter
import com.example.p5i.onlinegallery.databinding.FragmentTopicBinding
import com.example.p5i.onlinegallery.topicModule.viewModel.TopicsRecyclerAdpater
import com.example.p5i.onlinegallery.topicModule.viewModel.TopicsViewModel
import com.example.p5i.onlinegallery.topicModule.viewModel.TopicsViewModelFactory


private const val TAG = "TopicFragment"

class TopicFragment : Fragment() {

    private lateinit var topicViewModel: TopicsViewModel
    private lateinit var topicViewModelFactory: TopicsViewModelFactory
    private lateinit var loginCredential: LoginStateModel
    private lateinit var credential:String
    private lateinit var fragmentTopicBidning:FragmentTopicBinding
    private lateinit var topicViewModelAdapter: TopicsRecyclerAdpater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val acttiviy= requireNotNull(this.activity)

        fragmentTopicBidning= FragmentTopicBinding.inflate(inflater,container,false)
        fragmentTopicBidning.shimmerTopic.startShimmer()

        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        val topicFragmentNavController=findNavController()

        topicViewModelFactory= TopicsViewModelFactory(acttiviy.application,credential)
        topicViewModel=ViewModelProvider(this,topicViewModelFactory).get(TopicsViewModel::class.java)

        topicViewModelAdapter= TopicsRecyclerAdpater(TopicsRecyclerAdpater.TopicClicked {
            Log.d(TAG, "onCreateView: ${it.title}")
            topicFragmentNavController.navigate(TopicFragmentDirections.actionTopicFragmentToPhotosListFragment(it.slug,null))
        })
        topicViewModel.topics.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView: ${it.size}")
            if(!it.isEmpty())
            {
                topicViewModelAdapter.submitList(it)
                stopShimming()
            }
        })
        fragmentTopicBidning.root.findViewById<RecyclerView>(R.id.topicRecyclerView).apply {
            layoutManager=LinearLayoutManager(this@TopicFragment.context)
            adapter=topicViewModelAdapter
        }
        return fragmentTopicBidning.root
    }

    fun stopShimming()
    {
        fragmentTopicBidning.shimmerTopic.stopShimmer()
        fragmentTopicBidning.shimmerTopic.visibility=View.GONE
    }
}
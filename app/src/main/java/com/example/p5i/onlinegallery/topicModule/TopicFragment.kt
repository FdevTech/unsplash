package com.example.p5i.onlinegallery.topicModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.topicModule.viewModel.TopicsViewModel
import com.example.p5i.onlinegallery.topicModule.viewModel.TopicsViewModelFactory


private const val TAG = "TopicFragment"

class TopicFragment : Fragment() {

    private lateinit var topicViewModel: TopicsViewModel
    private lateinit var topicViewModelFactory: TopicsViewModelFactory
    private lateinit var loginCredential: LoginStateModel
    private lateinit var credential:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val acttiviy= requireNotNull(this.activity)
        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        topicViewModelFactory= TopicsViewModelFactory(acttiviy.application,credential)
        topicViewModel=ViewModelProvider(this,topicViewModelFactory).get(TopicsViewModel::class.java)
        topicViewModel.topics.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView: ${it.size}")
        })
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }


}
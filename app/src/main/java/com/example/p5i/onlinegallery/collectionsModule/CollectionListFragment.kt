package com.example.p5i.onlinegallery.collectionsModule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.collectionsModule.viewModel.CollectionViewModel
import com.example.p5i.onlinegallery.collectionsModule.viewModel.CollectionViewModelFactory

class CollectionListFragment : Fragment() {

     private lateinit var collectionViewModel:CollectionViewModel
    private lateinit var collecytionViewModelFactory: CollectionViewModelFactory
    private lateinit var loginCredential: LoginStateModel
    private lateinit var credential:String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        val activity= requireNotNull(this.activity)
        collecytionViewModelFactory= CollectionViewModelFactory(activity.application,credential)
        collectionViewModel=ViewModelProvider(this,collecytionViewModelFactory).get(CollectionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_collection_list, container, false)
    }


}
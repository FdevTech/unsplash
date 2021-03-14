package com.example.p5i.onlinegallery.photosModule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.databinding.FragmentPhotosListBinding
import com.example.p5i.onlinegallery.databinding.FragmentProfileBinding
import com.example.p5i.onlinegallery.photosModule.viewModel.PhotViewModelFactory
import com.example.p5i.onlinegallery.photosModule.viewModel.PhotoViewModel


class PhotosListFragment : Fragment() {
     lateinit var fragmentPhotosListBinding: FragmentPhotosListBinding
      lateinit var photoViewModelFactory: PhotViewModelFactory
     lateinit var photoViewModel:PhotoViewModel
     private lateinit var loginCredential: LoginStateModel
    private lateinit var credential:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentPhotosListBinding= FragmentPhotosListBinding.inflate(inflater,container,false)

        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"

        photoViewModelFactory= PhotViewModelFactory(credential)
        photoViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotoViewModel::class.java)
        
        return fragmentPhotosListBinding.root
    }


}
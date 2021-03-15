package com.example.p5i.onlinegallery.photosModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.databinding.FragmentPhotosListBinding
import com.example.p5i.onlinegallery.databinding.FragmentProfileBinding
import com.example.p5i.onlinegallery.databse.getDatabse
import com.example.p5i.onlinegallery.photosModule.ui.PhotoViewModelAdapter
import com.example.p5i.onlinegallery.photosModule.viewModel.PhotViewModelFactory
import com.example.p5i.onlinegallery.photosModule.viewModel.PhotoViewModel

private const val TAG = "PhotosListFragment"
class PhotosListFragment : Fragment() {

     lateinit var fragmentPhotosListBinding: FragmentPhotosListBinding
     lateinit var photoViewModelFactory: PhotViewModelFactory
     lateinit var photoViewModel:PhotoViewModel
     private lateinit var loginCredential: LoginStateModel
     private lateinit var credential:String
     private lateinit var photoViewModelAdapter: PhotoViewModelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentPhotosListBinding= FragmentPhotosListBinding.inflate(inflater,container,false)

        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
          val activity = requireNotNull(this.activity)
          photoViewModelFactory= PhotViewModelFactory(activity.application,credential)
          photoViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotoViewModel::class.java)
          photoViewModelAdapter=PhotoViewModelAdapter()




        fragmentPhotosListBinding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager=LinearLayoutManager(context)
            adapter=photoViewModelAdapter
        }
        photoViewModel.photosRetrived.observe(viewLifecycleOwner, Observer {

            photoViewModelAdapter.submitList(it)
        })

        return fragmentPhotosListBinding.root
    }


}
package com.example.p5i.onlinegallery.photosModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.databinding.FragmentPhotosListBinding
import com.example.p5i.onlinegallery.databinding.FragmentProfileBinding
import com.example.p5i.onlinegallery.photosModule.ui.OnPhotoClickListner
import com.example.p5i.onlinegallery.photosModule.ui.PhotoViewModelAdapter
import com.example.p5i.onlinegallery.photosModule.viewModel.PhotViewModelFactory
import com.example.p5i.onlinegallery.photosModule.viewModel.PhotosViewModel

private const val TAG = "PhotosListFragment"
class PhotosListFragment : Fragment() {

     lateinit var fragmentPhotosListBinding: FragmentPhotosListBinding
     lateinit var photoViewModelFactory: PhotViewModelFactory
     lateinit var photosViewModel:PhotosViewModel
     private lateinit var loginCredential: LoginStateModel
     private lateinit var credential:String
     private lateinit var photoViewModelAdapter: PhotoViewModelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentPhotosListBinding= FragmentPhotosListBinding.inflate(inflater,container,false)
        val navController=findNavController()
        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
          val activity = requireNotNull(this.activity)
          photoViewModelFactory= PhotViewModelFactory(activity.application,credential)
          photosViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotosViewModel::class.java)

          photoViewModelAdapter=PhotoViewModelAdapter(OnPhotoClickListner {data, position ->
              val extras= FragmentNavigatorExtras()
             Toast.makeText(this.context,"${data.id}",Toast.LENGTH_SHORT).show()
              Log.d(TAG, "onCreateView: ${data.id},")
              Log.d(TAG, "onCreateView: position  $position")
              navController.navigate(PhotosListFragmentDirections.actionPhotosListFragmentToPhotoFragment(data.id,position))
          })




        fragmentPhotosListBinding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager=LinearLayoutManager(context)
            adapter=photoViewModelAdapter
        }
        photosViewModel.photosRetrived.observe(viewLifecycleOwner, Observer {

            photoViewModelAdapter.submitList(it)
        })

        return fragmentPhotosListBinding.root
    }


}
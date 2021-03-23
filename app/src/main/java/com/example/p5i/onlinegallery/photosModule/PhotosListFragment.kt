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
import kotlin.math.log

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
        val args=PhotosListFragmentArgs.fromBundle(requireArguments())
        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        val activity = requireNotNull(this.activity)



        Log.d(TAG, "onCreateView: collection ${args.collections}")

        if(args.topics!=null || args.collections!=-1)
        {
            Log.d(TAG, "onCreateView: hello")
            if(args.topics!=null)
            {
                //todo logic for topics
                Log.d(TAG, "onCreateView: topic ${args.topics}")
                photoViewModelFactory= PhotViewModelFactory(activity.application,credential,topics = args.topics)
                photosViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotosViewModel::class.java)

                photosViewModel.topicsPhotosRetrived.observe(viewLifecycleOwner, Observer {

                    photoViewModelAdapter.submitList(it)

                })
            }
            else
            {
                //todo logic for collection
            }
        }
        else
        {
            Log.d(TAG, "onCreateView: fuck")
            photoViewModelFactory= PhotViewModelFactory(activity.application,credential)
            photosViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotosViewModel::class.java)
            photosViewModel.photosRetrived.observe(viewLifecycleOwner, Observer {

                photoViewModelAdapter.submitList(it)
            })
        }




          photoViewModelAdapter=PhotoViewModelAdapter(OnPhotoClickListner {data, position ->
              val extras= FragmentNavigatorExtras()
             Toast.makeText(this.context,"${data.id}",Toast.LENGTH_SHORT).show()
              navController.navigate(PhotosListFragmentDirections.actionPhotosListFragmentToPhotoFragment(data.id,position))
          })




        fragmentPhotosListBinding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager=LinearLayoutManager(context)
            adapter=photoViewModelAdapter
        }


        return fragmentPhotosListBinding.root
    }


}
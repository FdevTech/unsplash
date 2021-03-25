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
     private  var from:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentPhotosListBinding= FragmentPhotosListBinding.inflate(inflater,container,false)
        val navController=findNavController()
        val args=PhotosListFragmentArgs.fromBundle(requireArguments())
        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        val activity = requireNotNull(this.activity)



          Log.d(TAG, "onCreateView: collection ${args.collectionId}")
          fragmentPhotosListBinding.shimmer.startShimmer()
        if(args.topics!=null || args.collectionId!=null)
        {
            Log.d(TAG, "onCreateView: hello")
            if(args.topics!=null)
            {
                //todo logic for topics
                from="topics"
                Log.d(TAG, "onCreateView: topic ${args.topics}")
                photoViewModelFactory= PhotViewModelFactory(activity.application,credential,topics = args.topics)
                photosViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotosViewModel::class.java)

                photosViewModel.topicsPhotosRetrived.observe(viewLifecycleOwner, Observer {
                    //stopShimming()

                    if(!it.isEmpty())
                    {
                        stopShimming()
                        photoViewModelAdapter.submitList(it)
                    }
                    Log.d(TAG, "onCreateView:topic ${args.topics} it -> ${it.isEmpty()}")

                })
            }
            else
            {
                from="collection"
                photoViewModelFactory= PhotViewModelFactory(activity.application,credential,collectionId = args.collectionId)
                Log.d(TAG, "onCreateView: ${args.collectionId}")
                photosViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotosViewModel::class.java)

                photosViewModel.collectionPhotosRetrived.observe(viewLifecycleOwner, Observer {
                    //stopShimming()
                    if(!it.isEmpty())
                    {
                        stopShimming()
                        photoViewModelAdapter.submitList(it)
                    }
                    Log.d(TAG, "onCreateView: it -> ${it.isEmpty()}")
                    //photoViewModelAdapter.submitList(it)

                })
            }
        }
        /*else
        {
            from=null
            Log.d(TAG, "onCreateView: fuck")
            photoViewModelFactory= PhotViewModelFactory(activity.application,credential)
            photosViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotosViewModel::class.java)
            photosViewModel.photosRetrived.observe(viewLifecycleOwner, Observer {

                photoViewModelAdapter.submitList(it)
            })
        }*/




          photoViewModelAdapter=PhotoViewModelAdapter(OnPhotoClickListner {data, position,onFak ->
             Toast.makeText(this.context,"${data.id}",Toast.LENGTH_SHORT).show()
              Log.d(TAG, "onCreateView -> transitionname: ${data.id} \n $from")
             //navController.navigate(PhotosListFragmentDirections.actionPhotosListFragmentToPhotoFragment(data.id,position,from),extras)

              navController.navigate(PhotosListFragmentDirections.actionPhotosListFragmentToPhotoFragment(data.id,position,from))
          })




        fragmentPhotosListBinding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager=LinearLayoutManager(context)
            adapter=photoViewModelAdapter
        }


        return fragmentPhotosListBinding.root
    }

    fun stopShimming()
    {
        fragmentPhotosListBinding.shimmer.stopShimmer()
        fragmentPhotosListBinding.shimmer.visibility=View.GONE
    }

}
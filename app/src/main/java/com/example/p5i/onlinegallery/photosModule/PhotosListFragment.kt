package com.example.p5i.onlinegallery.photosModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.databinding.FragmentPhotosListBinding
import com.example.p5i.onlinegallery.databinding.FragmentProfileBinding
import com.example.p5i.onlinegallery.photosModule.ui.LikeClic
import com.example.p5i.onlinegallery.photosModule.ui.OnPhotoClickListner
import com.example.p5i.onlinegallery.photosModule.ui.PhotoViewModelAdapter
import com.example.p5i.onlinegallery.photosModule.ui.ProfileOnClick
import com.example.p5i.onlinegallery.photosModule.viewModel.PhotViewModelFactory
import com.example.p5i.onlinegallery.photosModule.viewModel.PhotosViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
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
        val navController=activity?.findNavController(R.id.creatingCollectionContainer)
        val navController_second=findNavController()
        val args=PhotosListFragmentArgs.fromBundle(requireArguments())
        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        val activity = requireNotNull(this.activity)



          Log.d(TAG, "onCreateView: collection ${args.collectionId}")
          fragmentPhotosListBinding.shimmer.startShimmer()
        if(args.topics!=null || args.collectionId!=null||args.user!=null)
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
                        photoViewModelAdapter.data=it
                    }
                    Log.d(TAG, "onCreateView:topic ${args.topics} it -> ${it.isEmpty()}")

                })
            }
            else if(args.collectionId!=null)
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
                        photoViewModelAdapter.data=it
                    }
                    Log.d(TAG, "onCreateView: it -> ${it.isEmpty()}")
                    //photoViewModelAdapter.submitList(it)

                })
            }
            else if (args.user!=null)
            {
               if(args.typeOfPhotos=="liked")
               {
                   from="userLikedPhoto"
                   Log.d(TAG, "onCreateView: ${args.user}, ${args.typeOfPhotos}")
                   photoViewModelFactory= PhotViewModelFactory(activity.application,credential,user = args.user, typeOfPhotos = args.typeOfPhotos)
                   photosViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotosViewModel::class.java)

                   photosViewModel.userLikedphotos.observe(viewLifecycleOwner, Observer {
                       //stopShimming()
                       if(!it.isEmpty())
                       {
                           stopShimming()
                           photoViewModelAdapter.data=it
                       }
                       Log.d(TAG, "onCreateView: it -> ${it.isEmpty()}")
                       //photoViewModelAdapter.submitList(it)

                   })
               }
                else if(args.typeOfPhotos=="userPhoto")
               {
                   from="userPhotos"
                   Log.d(TAG, "onCreateView: ${args.user}, ${args.typeOfPhotos}")
                   photoViewModelFactory= PhotViewModelFactory(activity.application,credential,user = args.user, typeOfPhotos = args.typeOfPhotos)
                   photosViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotosViewModel::class.java)

                   photosViewModel.userPhotos.observe(viewLifecycleOwner, Observer {
                       //stopShimming()
                       if(!it.isEmpty())
                       {
                           stopShimming()
                           photoViewModelAdapter.data=it
                       }
                       Log.d(TAG, "onCreateView: it -> ${it.isEmpty()}")
                       //photoViewModelAdapter.submitList(it)

                   })
               }
            }
        }




          photoViewModelAdapter=PhotoViewModelAdapter(OnPhotoClickListner {data, position,onFak ->
             Toast.makeText(this.context,"${data.id}",Toast.LENGTH_SHORT).show()
              Log.d(TAG, "onCreateView -> transitionname: ${data.id} \n $from")
             //navController.navigate(PhotosListFragmentDirections.actionPhotosListFragmentToPhotoFragment(data.id,position,from),extras)

              navController_second?.navigate(PhotosListFragmentDirections.actionPhotosListFragmentToPhotoFragment(data.id,position,from))
          })

        photoViewModelAdapter.profileOnClick= ProfileOnClick {

            navController?.navigate(R.id.profileFragment, bundleOf("user" to it.user_name))
            Log.d(TAG, "onCreateView: ${it.user_name}")
        }

        photoViewModelAdapter.likeClic= LikeClic {data,position->

            Log.d(TAG, "onCreateView: $data.id")
            photosViewModel.likeDesLikePhoto(data.id)
        }

        fragmentPhotosListBinding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager=LinearLayoutManager(context)
            adapter=photoViewModelAdapter
        }



        photosViewModel.eroorCode.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView: $it")
           when (it)
           {
               401->{
                Snackbar.make(fragmentPhotosListBinding.root, "something went wrong with API ",Snackbar.LENGTH_SHORT).show()
               }
               403->{
                   Snackbar.make(fragmentPhotosListBinding.root, "something went wrong please try latter",Snackbar.LENGTH_SHORT).show()
               }
               404->{
                   Snackbar.make(fragmentPhotosListBinding.root, "something went wrong your with your network",Snackbar.LENGTH_SHORT).show()
               }
           }
        })



        return fragmentPhotosListBinding.root
    }

    fun stopShimming()
    {
        fragmentPhotosListBinding.shimmer.stopShimmer()
        fragmentPhotosListBinding.shimmer.visibility=View.GONE
    }


}
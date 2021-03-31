package com.example.p5i.onlinegallery.homeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.databinding.FragmentHomeBinding
import com.example.p5i.onlinegallery.homeFragment.viewmodel.HomeFragmentViewModel
import com.example.p5i.onlinegallery.homeFragment.viewmodel.HomeFragmentViewModelFactory
import com.example.p5i.onlinegallery.photosModule.ui.*


private const val TAG = "HomeFragment"
class HomeFragment : Fragment() {

    lateinit var homeFragmentViewModelFactory: HomeFragmentViewModelFactory
    lateinit var homeFragmentViewModel: HomeFragmentViewModel
    private lateinit var loginCredential: LoginStateModel
    private lateinit var credential:String
    private lateinit var photoViewModelAdapter: PhotoViewModelAdapter
    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding= FragmentHomeBinding.inflate(inflater,container,false)
        fragmentHomeBinding.shimmerHome.startShimmer()
        

        val navController=findNavController()
        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        val activity = requireNotNull(this.activity)

        homeFragmentViewModelFactory=
            HomeFragmentViewModelFactory(
                activity.application,
                credential
            )
        homeFragmentViewModel=ViewModelProvider(this,homeFragmentViewModelFactory).get(
            HomeFragmentViewModel::class.java)
        homeFragmentViewModel.photosRetrived.observe(viewLifecycleOwner, Observer {

            if(!it.isEmpty())
            {
                photoViewModelAdapter.data=it
               // photoViewModelAdapter.submit(it)
               // photoViewModelAdapter.notifyDataSetChanged()

                stopShimmerHome()
            }
            Log.d(TAG, "onCreateView:=======> ${it.isEmpty()}")
        })
        photoViewModelAdapter= PhotoViewModelAdapter(OnPhotoClickListner{data,position,from ->
            Log.d(TAG, "onCreateView: $position")
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToPhotoFragment(
                    data.id,
                    position,
                    null
                )
            )
        })
        photoViewModelAdapter.profileOnClick= ProfileOnClick {

            navController.navigate(R.id.profileFragment, bundleOf("user" to it.user_name))
            Log.d(TAG, "onCreateView: ${it.user_name}")
        }
        
        photoViewModelAdapter.downloadClic= DownloadClic {

            Log.d(TAG, "onCreateView:downloadClic by using properties ")
        }
        
        photoViewModelAdapter.likeClic= LikeClic {data,position->
            Log.d(TAG, "onCreateView: by using properties ${data.id}")

            homeFragmentViewModel.likeDesLikePhoto(data.id)
           // photoViewModelAdapter.notifyItemChanged(position)

        }
        
        fragmentHomeBinding.homeRecyclerView.apply {
            layoutManager= LinearLayoutManager(context)
             adapter=photoViewModelAdapter
        }
        return fragmentHomeBinding.root
    }

    fun stopShimmerHome()
    {
        fragmentHomeBinding.shimmerHome.stopShimmer()
        fragmentHomeBinding.shimmerHome.visibility=View.GONE
    }

}
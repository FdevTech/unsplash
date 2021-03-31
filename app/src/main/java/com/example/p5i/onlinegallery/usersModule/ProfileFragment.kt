package com.example.p5i.onlinegallery.usersModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.databinding.FragmentProfileBinding
import com.example.p5i.onlinegallery.usersModule.viewmodel.ProfileViewModel
import com.example.p5i.onlinegallery.usersModule.viewmodel.ProfileViewModelFactory
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.log
private const val TAG = "ProfileFragment"

private lateinit var loginCredential: LoginStateModel
private lateinit var credential:String
private lateinit var profileViewModelFactory: ProfileViewModelFactory
private lateinit var profileViewModel: ProfileViewModel
private lateinit var fragmentProfileBinding:FragmentProfileBinding
class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        val activity= requireNotNull(this.activity)
        val args=ProfileFragmentArgs.fromBundle(requireArguments())

        Log.d(TAG, "onCreateView: ${args.user}")

        profileViewModelFactory=ProfileViewModelFactory(activity.application, credential,args.user)

        profileViewModel=ViewModelProvider(this, profileViewModelFactory).get(ProfileViewModel::class.java)

        fragmentProfileBinding= FragmentProfileBinding.inflate(inflater,container,false)

        profileViewModel.profiledata.observe(viewLifecycleOwner, Observer {

            Log.d(TAG, "onCreateView: ${it?.id}")
        })
        if(args.user!="me")
        {
            profileViewModel.photgrapherProfile.observe(viewLifecycleOwner, Observer {
                fragmentProfileBinding.profileDomain=it
            })
        }
        else {
            profileViewModel.profiledata.observe(viewLifecycleOwner, Observer {
                fragmentProfileBinding.profileDomain=it
            })
        }
        val navHost= childFragmentManager.findFragmentById(R.id.fuck) as NavHostFragment
        val controler= navHost.findNavController()
        controler.navigate(R.id.photosListFragment,bundleOf("user" to args.user,"typeOfPhotos" to "liked","collectionId" to null, "topics" to null))

       fragmentProfileBinding.navigationRail.setOnNavigationItemSelectedListener { item ->

           when(item.itemId)
           {
               R.id.userLikedPhoto ->{
                  //controler.navigate(R.id.userLikedPhoto)
                   Log.d(TAG, "onCreateView: userLikedPhoto")
                   controler.navigate(R.id.photosListFragment,bundleOf("user" to args.user,"typeOfPhotos" to "liked","collectionId" to null, "topics" to null))
                   true
               }
               R.id.userPhotos ->{
                   Log.d(TAG, "onCreateView: userPhotos")
                   controler.navigate(R.id.photosListFragment, bundleOf("user" to args.user,"typeOfPhotos" to "userPhoto","collectionId" to null, "topics" to null))
                   true
               }
               R.id.userCollection ->{
                   //controler.navigate(R.id.userCollection)
                   Log.d(TAG, "onCreateView: userCollection")
                   controler.navigate(R.id.collectionListFragment, bundleOf("user" to args.user))
                   true
               }
               else -> false
           }
       }



        val listner= AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            fragmentProfileBinding.constraintLayout.progress=seekPosition

        }
        fragmentProfileBinding.appbarLayout.addOnOffsetChangedListener(listner)



        return fragmentProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}
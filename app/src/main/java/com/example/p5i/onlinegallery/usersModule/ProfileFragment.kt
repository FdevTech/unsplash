package com.example.p5i.onlinegallery.usersModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.databinding.FragmentProfileBinding
import com.example.p5i.onlinegallery.usersModule.viewmodel.ProfileViewModel
import com.example.p5i.onlinegallery.usersModule.viewmodel.ProfileViewModelFactory
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

        profileViewModelFactory=ProfileViewModelFactory(activity.application, credential)

        profileViewModel=ViewModelProvider(this, profileViewModelFactory).get(ProfileViewModel::class.java)

        fragmentProfileBinding= FragmentProfileBinding.inflate(inflater,container,false)

        profileViewModel.profiledata.observe(viewLifecycleOwner, Observer {

            Log.d(TAG, "onCreateView: ${it?.id}")
        })
         profileViewModel.profiledata.observe(viewLifecycleOwner, Observer {
             fragmentProfileBinding.profileDomain=it
         })

        return fragmentProfileBinding.root
    }


}
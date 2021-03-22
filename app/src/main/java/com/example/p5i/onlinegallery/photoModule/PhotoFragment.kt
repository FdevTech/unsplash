package com.example.p5i.onlinegallery.photoModule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.databinding.FragmentPhotoBinding
import com.example.p5i.onlinegallery.photoModule.ui.DepthPageTransformer
import com.example.p5i.onlinegallery.photoModule.ui.PhotoViewAdapter
import com.example.p5i.onlinegallery.photoModule.viewmodel.PhotoViewModel
import com.example.p5i.onlinegallery.photoModule.viewmodel.PhotoViewModelFactory
import com.example.p5i.onlinegallery.photosModule.domain.PhotoDomain


private const val TAG = "PhotoFragment"
class PhotoFragment : Fragment() {
  
    lateinit var photoViewModelFactory: PhotoViewModelFactory
    lateinit var photoViewModel: PhotoViewModel
    private lateinit var loginCredential: LoginStateModel
    private lateinit var credential:String
    private lateinit var fragmentPhotoBinding:FragmentPhotoBinding
    private lateinit var photoViewAdapter: PhotoViewAdapter
    private  lateinit var listOfPhotos:List<PhotoDomain>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val activity = requireNotNull(this.activity)
        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        val args=PhotoFragmentArgs.fromBundle(requireArguments())

        fragmentPhotoBinding= FragmentPhotoBinding.inflate(inflater,container,false)

        photoViewModelFactory= PhotoViewModelFactory(activity.application,credential)
        photoViewModel=ViewModelProvider(this,photoViewModelFactory).get(PhotoViewModel::class.java)

        photoViewAdapter=PhotoViewAdapter()

        photoViewModel.photosRetrived.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView: ${it.size}")
            listOfPhotos=it
            photoViewAdapter.submitList((listOfPhotos.subList(args.position,listOfPhotos.size)))
           // photoViewAdapter.submitList(it)
        })
        fragmentPhotoBinding.bigPhotRecyclerView.apply {

            adapter=photoViewAdapter
            setPageTransformer(DepthPageTransformer())
           // currentItem=4
            photoViewAdapter.notifyDataSetChanged()

        }




        return fragmentPhotoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentPhotoBinding.bigPhotRecyclerView.setCurrentItem(3)
    }


}
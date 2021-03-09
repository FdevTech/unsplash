package com.example.p5i.onlinegallery

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.transition.Explode
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.collectionsModule.datlayer.CollectionAPI
import com.example.p5i.onlinegallery.photosModule.datalayer.Photos
import com.example.p5i.onlinegallery.usersModule.dtaLayer.network_Profile
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.platform.MaterialContainerTransform
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var creatingCollectionContainer:FragmentContainerView
    lateinit var fab:FloatingActionButton
    lateinit var content:ViewGroup
    lateinit var loginCredential: LoginStateModel
    override fun onCreate(savedInstanceState: Bundle?) {
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
             enterTransition= Explode()
            // set an exit transition
            exitTransition = Explode()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        creatingCollectionContainer=findViewById(R.id.creatingCollectionContainer)
        fab=findViewById(R.id.fab)
        content=findViewById(R.id.content)

        fab.setOnClickListener { view: View? ->

            val transform=MaterialContainerTransform().apply {
                startView=fab
                endView=creatingCollectionContainer
                addTarget(endViewId)
                scrimColor= Color.TRANSPARENT
                duration=1500

            }
            TransitionManager.beginDelayedTransition(creatingCollectionContainer,transform)
            fab.visibility=View.GONE
            creatingCollectionContainer.visibility=View.VISIBLE


        }

    }

    override fun onBackPressed() {
       // super.onBackPressed()

        val transform=MaterialContainerTransform().apply {
            endView=fab
            startView=creatingCollectionContainer
            addTarget(endViewId)
            scrimColor= Color.TRANSPARENT
            duration=1500
        }
        TransitionManager.beginDelayedTransition(creatingCollectionContainer,transform)
        fab.visibility=View.VISIBLE
        creatingCollectionContainer.visibility=View.GONE


    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch{

            loginCredential= LoginStateModel(this@MainActivity)


           try {
              // val value=CollectionAPI.retrofitService.addPhototoaAcoolection("Bearer ${loginCredential.retriveTockenl()}")
              // CollectionAPI.retrofitService.deletePhotoFromaAcoolection("Bearer ${loginCredential.retriveTockenl()}")
               val value= network_Profile.ProfileAPI.profile.updateMyInfo("Bearer ${loginCredential.retriveTockenl()}")
               //val value=CollectionAPI.retrofitService.getCollectionPhotos("Bearer ${loginCredential.retriveTockenl()}")
               Log.d(TAG, "onResume: ${loginCredential.retriveTockenl()}")
               Log.d(TAG, "onResume: "+value.name)
           }catch (t:Throwable)
           {
               Log.d(TAG, "onResume: ${t.message}")
           }


        }

    }
}
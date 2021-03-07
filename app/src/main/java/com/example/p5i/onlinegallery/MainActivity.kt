package com.example.p5i.onlinegallery

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Explode
import android.transition.Fade
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.FragmentContainerView
import com.example.p5i.onlinegallery.collectionsModule.collectionListPackage.CollectionAPI
import com.example.p5i.onlinegallery.collectionsModule.collectionListPackage.CollectionPOJ
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var creatingCollectionContainer:FragmentContainerView
    lateinit var fab:FloatingActionButton
    lateinit var content:ViewGroup
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
        CollectionAPI.retrofitService.getCollection("Client-ID CH5YIV_t-PtFB52Db4bAXGQxiQEVy79ZTy9wa4z90iQ").enqueue(object:Callback<List<CollectionPOJ>>{
            override fun onFailure(call: Call<List<CollectionPOJ>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

            override fun onResponse(call: Call<List<CollectionPOJ>>, response: Response<List<CollectionPOJ>>) {
                Log.d(TAG, "onResponse: ${response.body()?.get(0)?.id}")
            }

        })
    }
}
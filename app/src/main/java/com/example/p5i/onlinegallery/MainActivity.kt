package com.example.p5i.onlinegallery

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.topicModule.networkLayer.TopicsAPI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.platform.MaterialContainerTransform
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var creatingCollectionContainer:FragmentContainerView
    lateinit var content:ViewGroup
    lateinit var loginCredential: LoginStateModel
    lateinit var  contoller:NavController
    lateinit var navHost:NavHostFragment
    lateinit var bottomNavigationMenu:BottomNavigationView
    val bundle = bundleOf("topics" to null)
    override fun onCreate(savedInstanceState: Bundle?) {
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            enterTransition= Explode()
            exitTransition = Explode()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        creatingCollectionContainer=findViewById(R.id.creatingCollectionContainer)

        content=findViewById(R.id.content)


         navHost=supportFragmentManager.findFragmentById(R.id.creatingCollectionContainer) as NavHostFragment
         contoller=navHost.navController

        contoller.setGraph(R.navigation.navigation,bundle)
        bottomNavigationMenu=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationMenu.setupWithNavController(contoller)


    }

    override fun onResume() {
        super.onResume()
        bottomNavigationMenu.setOnNavigationItemSelectedListener {
            when( it.itemId)
            {
                R.id.homeFragment ->{
                    Log.d(TAG, "onResume: homeFragment clicked")
                    contoller.navigate(R.id.homeFragment)
                    true
                }
                R.id.collectionListFragment ->{
                    Log.d(TAG, "onResume: collectionListFragment")
                   // contoller.navigate(R.id.collectionListFragment, bundleOf("user" to null))
                    contoller.navigate(R.id.action_global_collectionListFragment,bundleOf("user" to null))
                    true
                }
                R.id.topicFragment ->{
                    Log.d(TAG, "onResume: topicFragment")
                   // contoller.navigate(R.id.topicFragment)
                    contoller.navigate(R.id.action_global_topicFragment)
                    true
                }
                R.id.profileFragment ->{
                    Log.d(TAG, "onResume: profileFragment")
                    //contoller.navigate(R.id.profileFragment, bundleOf("user" to "me"))
                    contoller.navigate(R.id.action_global_profileFragment,bundleOf("user" to "me"))
                    true
                }
                else ->
                {
                    false
                }
            }
        }
    }




}

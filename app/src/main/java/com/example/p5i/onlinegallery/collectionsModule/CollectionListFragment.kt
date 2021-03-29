package com.example.p5i.onlinegallery.collectionsModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.p5i.onlinegallery.R
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel
import com.example.p5i.onlinegallery.collectionsModule.ui.CollectionClicked
import com.example.p5i.onlinegallery.collectionsModule.ui.CollectionViewModelAdapter
import com.example.p5i.onlinegallery.collectionsModule.viewModel.CollectionViewModel
import com.example.p5i.onlinegallery.collectionsModule.viewModel.CollectionViewModelFactory
import com.example.p5i.onlinegallery.databinding.FragmentCollectionListBinding

private const val TAG = "CollectionListFragment"
class CollectionListFragment : Fragment() {

    private lateinit var collectionViewModel:CollectionViewModel
    private lateinit var collecytionViewModelFactory: CollectionViewModelFactory
    private lateinit var loginCredential: LoginStateModel
    private lateinit var credential:String
    private lateinit var collectionViewModelAdapter: CollectionViewModelAdapter

    private lateinit var bindingColectionFragment:FragmentCollectionListBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingColectionFragment= FragmentCollectionListBinding.inflate(inflater,container,false)
        val collectionControler=findNavController()
        val args=CollectionListFragmentArgs.fromBundle(requireArguments())
        Log.d(TAG, "onCreateView: user  => ${args.user}")

        bindingColectionFragment.shimmerCollection.startShimmer()

        loginCredential= LoginStateModel(context)
        credential="Bearer ${loginCredential.retriveTockenl()}"
        val activity= requireNotNull(this.activity)

        collecytionViewModelFactory= CollectionViewModelFactory(activity.application,credential,args.user)
        collectionViewModel=ViewModelProvider(this,collecytionViewModelFactory).get(CollectionViewModel::class.java)

        collectionViewModelAdapter=CollectionViewModelAdapter(CollectionClicked {
            Log.d(TAG, "onCreateView: ${it.id}")
            collectionControler.navigate(CollectionListFragmentDirections.actionCollectionListFragmentToPhotosListFragment(null,it.id,null,null))
        })

        bindingColectionFragment.root.findViewById<RecyclerView>(R.id.collectionRecyclerView).apply {
             layoutManager=LinearLayoutManager(this@CollectionListFragment.context)
             adapter=collectionViewModelAdapter
        }


        if(args.user!=null)
        {
            collectionViewModel.userCollections.observe(viewLifecycleOwner, Observer {
                if(!it.isEmpty())
                {
                    stopShimerInCollection()
                    collectionViewModelAdapter.submitList(it)
                }
                Log.d(TAG, "onCreateView:is Empty ${it.isEmpty()}")
            })
        }
        else
        {
            collectionViewModel.collections.observe(viewLifecycleOwner, Observer {
                if(!it.isEmpty())
                {
                    stopShimerInCollection()
                    collectionViewModelAdapter.submitList(it)
                }
                Log.d(TAG, "onCreateView: ${it.isEmpty()}")
            })
        }


        return bindingColectionFragment.root
    }

fun stopShimerInCollection()
{
    bindingColectionFragment.shimmerCollection.stopShimmer()
    bindingColectionFragment.shimmerCollection.visibility=View.GONE
}
}
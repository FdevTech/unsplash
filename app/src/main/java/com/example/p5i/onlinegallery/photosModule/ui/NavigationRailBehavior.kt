package com.example.p5i.onlinegallery.photosModule.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ScrollView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import com.google.android.material.navigationrail.NavigationRailView

private const val TAG = "NavigationRailBehavior"
class NavigationRailBehavior(context: Context ,attrs:AttributeSet): CoordinatorLayout.Behavior<NavigationRailView>(context,attrs)
{
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: NavigationRailView,
        dependency: View
    ): Boolean {
        return dependency is NestedScrollView
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: NavigationRailView,
        dependency: View
    ): Boolean {

        val scrollUp=false
        dependency.setOnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrollY->
            var i=0
            if(oldScrollY<scrollY)
            {

                if(scrollY<300f)
                {
                    child.translationX=-scrollY.toFloat()
                }

            }
            else
            {

                child.translationX=0f

                Log.d(TAG, "onDependentViewChanged: ${oldScrollY-scrollY}")

            }
        }

        return true
    }
}
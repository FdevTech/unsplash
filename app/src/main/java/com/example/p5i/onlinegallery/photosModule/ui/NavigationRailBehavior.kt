package com.example.p5i.onlinegallery.photosModule.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ScrollView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigationrail.NavigationRailView

private const val TAG = "NavigationRailBehavior"
class NavigationRailBehavior(context: Context ,attrs:AttributeSet): CoordinatorLayout.Behavior<NavigationRailView>(context,attrs)
{
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: NavigationRailView,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: NavigationRailView,
        dependency: View
    ): Boolean {
        val view=dependency as AppBarLayout
        val range=view.totalScrollRange
        val factory=(view.y/range)+1.0
        val width=child.width

        child.translationX=(width*(factory-1f)).toFloat()


        return true
    }
}
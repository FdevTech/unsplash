package com.example.p5i.onlinegallery.authenticationModule.fabView;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Pair;

import com.example.p5i.onlinegallery.R;
import com.example.p5i.onlinegallery.authenticationModule.fabView.FabSateModel;

import androidx.lifecycle.MutableLiveData;

public class FabAnimationModel
{
    private static final String TAG = "FabAnimationModel";


    private Drawable icon;
    private MutableLiveData<Boolean> FabEnabledStateLiveData,FabExtendStateLiveData;
    private static AnimatedVectorDrawable animatedVectorDrawable;

    private FabSateModel mFabSateModel;


    public FabAnimationModel(Context context)
    {
        icon=context.getDrawable(R.drawable.avd_anim);
        animatedVectorDrawable=(AnimatedVectorDrawable) icon;
        FabEnabledStateLiveData=new MutableLiveData<>();
        FabExtendStateLiveData=new MutableLiveData<>();
        mFabSateModel=new FabSateModel(context);
        FabEnabledStateLiveData.setValue(mFabSateModel.retriveFabenableddState());
        FabExtendStateLiveData.setValue(mFabSateModel.retriveFabExtendState());

    }

    public void animateIconInFab()
    {

        animatedVectorDrawable.start();


    }
    public Drawable getIcon()
    {
        return icon;
    }

   public void enbaleFab(boolean enable)
   {


       mFabSateModel.setFabenableddState(enable);
       FabEnabledStateLiveData.setValue(enable);
   }
   public void extendedFab(boolean extend)
   {
       mFabSateModel.setFabExtendState(extend);
       FabExtendStateLiveData.setValue(extend);
   }
    public MutableLiveData<Boolean> getFabEnabledStateLiveData()
    {
        return FabEnabledStateLiveData;
    }

    public MutableLiveData<Boolean> getFabExtendStateLiveData() {

        return FabExtendStateLiveData;
    }

    public Pair<Boolean,Boolean>  getState()
    {
        return new Pair<>(mFabSateModel.retriveFabenableddState(),mFabSateModel.retriveFabExtendState());
    }


}

package com.example.p5i.onlinegallery.authenticationModule;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.text.BoringLayout;

import com.example.p5i.onlinegallery.R;

import androidx.lifecycle.MutableLiveData;

public class FabAnimationModel
{
    private boolean extend=true;
    private Drawable icon;
    private MutableLiveData<Boolean> FabEnabledStateLiveData,FabExtendStateLiveData;
    private static AnimatedVectorDrawable animatedVectorDrawable;

    public FabAnimationModel(Context context)
    {
        icon=context.getDrawable(R.drawable.avd_anim);
        animatedVectorDrawable=(AnimatedVectorDrawable) icon;
        FabEnabledStateLiveData=new MutableLiveData<>();
        FabExtendStateLiveData=new MutableLiveData<>();
        FabExtendStateLiveData.setValue(true);
        FabEnabledStateLiveData.setValue(false);
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
       FabEnabledStateLiveData.setValue(enable);
   }
   public void extendedFab(boolean extend)
   {
       FabExtendStateLiveData.setValue(extend);
   }
    public MutableLiveData<Boolean> getFabEnabledStateLiveData()
    {
        return FabEnabledStateLiveData;
    }

    public MutableLiveData<Boolean> getFabExtendStateLiveData() {
        return FabExtendStateLiveData;
    }
}

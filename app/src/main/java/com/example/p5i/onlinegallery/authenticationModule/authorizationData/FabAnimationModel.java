package com.example.p5i.onlinegallery.authenticationModule.authorizationData;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;

import com.example.p5i.onlinegallery.R;

public class FabAnimationModel
{
    private boolean extend=true;
    private Drawable icon;
    private static AnimatedVectorDrawable animatedVectorDrawable;

    public FabAnimationModel(Context context)
    {
        icon=context.getDrawable(R.drawable.avd_anim);
        animatedVectorDrawable=(AnimatedVectorDrawable) icon;
    }

    public void animateIconInFab()
    {
        animatedVectorDrawable.start();
    }
    public Drawable getIcon() {
        return icon;
    }
}
